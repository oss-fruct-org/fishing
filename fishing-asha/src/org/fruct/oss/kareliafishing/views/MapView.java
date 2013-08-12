/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fruct.oss.kareliafishing.views;

import com.nokia.maps.common.GeoCoordinate;
import com.nokia.maps.map.MapMarker;
import com.nokia.maps.map.MapCanvas;
import com.nokia.maps.map.Point;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import org.fruct.oss.kareliafishing.Localization;

/**
 *
 * @author davidson
 */
public class MapView extends MapCanvas implements CommandListener {
    
    private Alert reconnectAlert;
    private Command no;
    private Command yes;
    private Command back;
    private Localization strings;
    private final static double ZOOM_LEVEL = 12.0;
    private final GeoCoordinate PetrozavodskCoordinates = 
            new GeoCoordinate(61.7833, 34.35, 0);
    
    public interface Listener {
        void back();
    }
    
    private Listener listener;

    public MapView(Display display, Localization strings, Listener listener) {
        super(display);
             
        this.strings = strings;
        this.listener = listener;
        
        reconnectAlert = new Alert(strings.localize("conerr", "Connection Error"));
        no = new Command(strings.localize("no", "No"), Command.BACK, 1);
        yes = new Command(strings.localize("yes", "Yes"), Command.OK, 1);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        
        // set default map properties        
        map.setCenter(PetrozavodskCoordinates);
        map.setZoomLevel(ZOOM_LEVEL, 0, 0);
        
        this.addCommand(back);
        this.setCommandListener(this);
    }
    
    public void addMarker(Image image, double latitude, double longitude) {
        GeoCoordinate gc = new GeoCoordinate(latitude, longitude, 0);

        // create marker with custom icon
        MapMarker mm = mapFactory.createMapMarker(gc, image);

        // set anchor point to center the icon
        Point anchor = new Point(image.getWidth() / 2,
                image.getHeight() / 2);

        mm.setAnchor(anchor);
        map.setCenter(gc);
        map.addMapObject(mm);
    }
    
    public void clearMap() {
        map.removeAllMapObjects();
        map.setZoomLevel(ZOOM_LEVEL, 0, 0);
    }
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
           listener.back();        
        } else if (command == yes) {
            this.getMapDisplay().reconnect();
            display.setCurrent(this);
        } else if (command == no) {
            display.setCurrent(this);
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
}
