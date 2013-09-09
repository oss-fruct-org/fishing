package org.fruct.oss.kareliafishing.views;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.models.Fish;

/**
 *
 * @author Nikita Davydovskii
 * date: 29.07.2013
 * This view contains list of all fishes info according to giving lake.
 */
public class FishesListView extends BaseListView implements CommandListener {
    
    private Command back;

    public FishesListView(Localization strings, Listener listener) {
        super(null, strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
    }
    
    public void setInfo(Vector fishes) {
        Fish fish;
        for (int i = 0; i < fishes.size(); i++) {
            fish = (Fish)fishes.elementAt(i);
            this.append(fish.getName(), null);
        }
    }
   
    public void commandAction(Command command, Displayable displayable) {
        if (command == SELECT_COMMAND) {
            getListener().changeView(MainController.FISH_INFO_FORM_VIEW);
        }
        if (command == back) {
            getListener().back();
        }
    }  
}
