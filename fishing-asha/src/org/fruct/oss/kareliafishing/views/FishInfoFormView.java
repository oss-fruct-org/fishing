package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.models.Fish;

/**
 *
 * @author Nikita Davydovskii
 * date: 29.07.13
 * This view contains all information that available for the given fish.
 */
public class FishInfoFormView extends BaseFormView implements CommandListener {
    
    private Command back;

    public FishInfoFormView(Localization strings, Listener listener) {
        super(null, strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);  
    }
    
    public void setInfo(Fish fish) {
        this.append(fish.getPicture());
        this.append(fish.getFishInfo());
    }
    
    
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
            getListener().changeView(MainController.FISHES_LIST_VIEW);
        }
    }
    
}
