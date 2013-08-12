package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.models.Lake;

/**
 *
 * @author Nikita Davydovskii
 * date: 29.07.2013
 * This view contains all information that available for the given lake.
 */
public class LakeInfoFormView extends BaseFormView implements CommandListener {
    
    private Command back;

    public LakeInfoFormView(Localization strings, Listener listener) {
        super(strings.localize("infoitem", "Information"), strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);        
    }
    
    public void setInfo(Lake lake) {
        this.append(lake.getName() + "\n");
        this.append(getStrings().localize("coords", "Coordinates") + "\n" + 
                lake.getLatitude() + ", " + lake.getLongitude() + "\n");
        this.append(lake.getDescription());     
    }
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
            getListener().changeView(MainController.LAKE_CHOISE_LIST_VIEW);
        }
    }
}
