package org.fruct.oss.kareliafishing.mapcomponents;

import com.nokia.maps.component.touch.button.ImageButton;
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 *
 * @author Nikita Davydovskii
 * date: 26.08.2013
 * This class is button which placed on the map. It provides ability to user 
 * to select which type of objects he wants to see on the map.
 */
public class ObjectsTypeSelectorButton extends ImageButton {
    
    /**
     * Unique ID for the Type Selector Component.
     */
    public static final String ID = "TypeSelector";
    
    private Listener listener;

    public ObjectsTypeSelectorButton(Listener listener) throws IOException {
        super(ID, Graphics.BOTTOM | Graphics.RIGHT, 
                Image.createImage("/map_settings.png"), 
                Image.createImage("/map_settings_e.png"));
        this.listener = listener;
    }
    
    public void toggleButton() {
        listener.setObjectsType();
    }
    
    public interface Listener {
        void setObjectsType();
    }
}
