package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.MainController;
import org.netbeans.microedition.lcdui.SplashScreen;

/**
 *
 * @author Nikita Davydovskii
 * date: 25.07.13
 * This class is a wraper for NetBeans SplashScreen class.
 */
public class SplashScreenWrapperView extends SplashScreen implements CommandListener {
    
    private final SplashScreenWrapperView.Listener listener;

    public SplashScreenWrapperView(Display display, 
            SplashScreenWrapperView.Listener listener) 
            throws IllegalArgumentException {
        super(display);
        this.listener = listener;
        this.setCommandListener(this);
    }
   
    public void commandAction(Command command, Displayable displayable) {
        if (command == SplashScreen.DISMISS_COMMAND) {
            listener.startMainView();
        }
    }
    
    public interface Listener {
        void startMainView();
    }  
}
