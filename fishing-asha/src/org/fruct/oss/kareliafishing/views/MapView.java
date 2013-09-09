/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fruct.oss.kareliafishing.views;

import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.component.feedback.FocalEventListener;
import com.nokia.maps.component.feedback.FocalObserverComponent;
import com.nokia.maps.component.touch.InfoBubbleComponent;
import com.nokia.maps.map.MapCanvas;
import com.nokia.maps.map.MapMarker;
import com.nokia.maps.map.MapObject;
import com.nokia.maps.map.Point;
import java.io.IOException;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.mapcomponents.ObjectsTypeSelectorButton;

/**
 *
 * @author davidson
 */
public class MapView extends MapCanvas implements CommandListener {
    
    private Alert reconnectAlert;
    private Command about;
    private Command viewList;
    private Command no;
    private Command yes;
    private Localization strings;
    private final static double START_ZOOM_LEVEL = 7.0;
    private final static double OBJECT_ZOOM_LEVEL = 12.0;
    private final GeoCoordinate PetrozavodskCoordinates = 
            new GeoCoordinate(61.7833, 34.35, 0);
     
    private ObjectsTypeSelectorButton objectsTypeSelectorButton;
    
    private boolean pressed = false;
    
    public interface Listener {
        void changeView(int view);
             
        void sendClickedObject(MapObject mapObject);
        
        void showViewList();
    }
    
    private Listener listener;

    public MapView(Display display, Localization strings, Listener listener) {
        super(display);
        
        this.setTitle(strings.localize("maintitle", "Karelia Fishing"));
             
        this.strings = strings;
        this.listener = listener;
        
        about = new Command(strings.localize("about", "About"), Command.SCREEN, 2);
        viewList = new Command(strings.localize("viwelist", "View list"), 
                Command.SCREEN, 1);
        
        this.addCommand(about);
        this.addCommand(viewList);
        
        reconnectAlert = new Alert(strings.localize("conerr", "Connection Error"));
        no = new Command(strings.localize("no", "No"), Command.BACK, 1);
        yes = new Command(strings.localize("yes", "Yes"), Command.OK, 1);
        
            
        // set default map properties        
        map.setCenter(PetrozavodskCoordinates);
        map.setZoomLevel(START_ZOOM_LEVEL, 0, 0);
       
        
        try {
            objectsTypeSelectorButton = new ObjectsTypeSelectorButton(
                    new ObjectsTypeSelectorButton.Listener() {

                public void setObjectsType() {
                    setObjectsTypeMap();
                }
            });
            map.addMapComponent(objectsTypeSelectorButton);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
         
        this.setCommandListener(this);
    }
    
    public void setObjectsTypeMap() {
        listener.changeView(MainController.SELECTOR_VIEW);
    }
    
    public MapMarker createMarker(Image image, GeoCoordinate geoCoordinate) {
        return mapFactory.createMapMarker(geoCoordinate, image);
    }
    
    public void addMarker(MapMarker marker) {     
        map.addMapObject(marker);
    }
    
    public void clearMap() {
        map.removeAllMapObjects();
        map.setZoomLevel(START_ZOOM_LEVEL, 0, 0);
    }
    
    public void setCenter() {
        map.setCenter(PetrozavodskCoordinates);
        map.setZoomLevel(START_ZOOM_LEVEL, 0, 0);
    }
    
    public void setMapCenterToObject(GeoCoordinate geoCoordinate) {
        map.setCenter(geoCoordinate);
        map.setZoomLevel(OBJECT_ZOOM_LEVEL, 0, 0);
    }
  
    public void commandAction(Command command, Displayable displayable) {
        if (command == yes) {
            this.getMapDisplay().reconnect();
            display.setCurrent(this);
        } else if (command == no) {
            display.setCurrent(this);
        } else if (command == about) {
            listener.changeView(MainController.ABOUT_VIEW);
        } else if (command == viewList) {
            listener.showViewList();
        }
    } 
    
    /**
     * If something went wrong with map
     *
     * @param description
     *            the description of the source of the error
     * @param detail
     *            the exception detail, such as IOException etc
     * @param critical
     *            if this is critical, always true
     */
    public void onMapUpdateError(String description, Throwable detail, 
            boolean critical) {
        reconnectAlert.setTimeout(Alert.FOREVER);

        StringBuffer buf = new StringBuffer(
                detail.getMessage() != null
                        ? detail.getMessage()
                        : detail.getClass().getName());

        buf.append("\n");
        buf.append(strings.localize("reconquest", "Do you wish to reconnect?"));

        reconnectAlert.setTitle(
                detail.getMessage() != null
                        ? detail.getClass().getName()
                        : strings.localize("conerr", "Connection Error"));

        reconnectAlert.setString(buf.toString());
        reconnectAlert.addCommand(no);
        reconnectAlert.addCommand(yes);
        reconnectAlert.setCommandListener(this);

        // It may be instructive to view the stack trace to find the cause
        // of the error.
        detail.printStackTrace();

        display.setCurrent(reconnectAlert);
    }

    /**
     * This means that the all tiles are present and completely rendered with
     * all objects present.
     */
    public void onMapContentComplete() {
        //System.out.println("Map successfully loaded");
    }

    protected void pointerPressed(int x, int y) {
        super.pointerPressed(x, y);
        pressed = true;
    }
    
    protected void pointerDragged(int x, int y) {
        super.pointerDragged(x, y);
        pressed = false;
    }

    protected void pointerReleased(int x, int y) {
        super.pointerReleased(x, y);
        if (pressed) {
            Point clickPoint = new Point(x, y);
            MapObject mapObject = map.getObjectAt(clickPoint);
            if (mapObject != null) {
                listener.sendClickedObject(mapObject);
            }         
        }
    }  
}
