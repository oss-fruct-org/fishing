package org.fruct.oss.kareliafishing.views;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.models.Hostel;

/**
 *
 * @author Nikita Davydovskii
 * date: 29.07.13
 * This view contains list of all hostels.
 */
public class HostelsListView extends BaseListView implements CommandListener {
    
    private Command back;

    public HostelsListView(Localization strings, Vector hostels, Listener listener) {
        super(strings.localize("geotype-2", "Hostels"), strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
        
        initUI(hostels);
    }
    
    private void initUI(Vector hostels) {
        Hostel hostel;
        for (int i = 0; i < hostels.size(); i++) {
            hostel = (Hostel)hostels.elementAt(i);
            this.append(hostel.getName(), null);
        }
    }
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == SELECT_COMMAND) {
            getListener().changeView(MainController.HOSTEL_CHOISE_LIST_VIEW);
        }
        if (command == back) {
            getListener().changeView(MainController.CATEGORY_LIST_VIEW);
        }
    }
    
    
    
}
