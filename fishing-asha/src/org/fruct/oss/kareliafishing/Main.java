package org.fruct.oss.kareliafishing;

import com.nokia.maps.common.ApplicationContext;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class Main extends MIDlet {
    
    private static Main main;
    private MainController mainController;
    
    public void startApp() {
        main = this;
        
        Display display = Display.getDisplay(this);
        
        ApplicationContext.getInstance().setAppID("HF0K-1DzItC5oT2TutKY");
        ApplicationContext.getInstance().setToken("BpD6k2KX0Q8uvAviKIwH5Q");
        
        mainController = new MainController(display);
    }
    
    public static Main getInstance() {
        return main;
    }
   
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }
    
    public void exitMIDlet() {
        destroyApp(true);
        notifyDestroyed();
    }
}
