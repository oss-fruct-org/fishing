package org.fruct.oss.kareliafishing.views;

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
 * This view contains all information that available for the given hostel.
 */
public class HostelInfoFormView extends BaseFormView implements CommandListener {
    
    private Command back;

    public HostelInfoFormView(Localization strings, Listener listener) {
        super(strings.localize("infoitem", "Information"), strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
    }
    
    public void setInfo(Hostel hostel) {
        this.append(hostel.getName() + "\n");
        this.append(getStrings().localize("coords", "Coordinates") + "\n" + 
                hostel.getLatitude() + ", " + hostel.getLongitude() + "\n");
        this.append(hostel.getDescription() + "\n");
        this.append(getStrings().localize("tel", "Telephone") + "\n" + 
                hostel.getPhone() + "\n");
        this.append(getStrings().localize("site", "Site") + "\n" + 
                hostel.getSite());
        
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
            getListener().changeView(MainController.HOSTEL_CHOISE_LIST_VIEW);
        }
    }   
}
