package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.Localization;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.2013
 * This class is a view which provides information about the application.
 */
public class AboutFormView extends BaseFormView implements CommandListener {
    
    private Command BACK;

    public AboutFormView(Localization strings, Listener listener) {
        super(strings.localize("about", "About"), strings, listener);
        
        BACK = new Command(getStrings().localize("back", "Back"), Command.BACK, 0);      
        
        this.addCommand(BACK);
        this.setCommandListener(this);
    }
  
    public void commandAction(Command command, Displayable displayable) {
        if (command == BACK) {
            getListener().changeView(MainController.CATEGORY_LIST_VIEW);
        }
    }

}
