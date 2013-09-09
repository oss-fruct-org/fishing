package org.fruct.oss.kareliafishing.views;

import java.util.Vector;
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
 * This view contains list of all lakes.
 */
public class LakesListView extends BaseListView implements CommandListener {
    
    private Command back;

    public LakesListView(Localization strings, Vector lakes, Listener listener) {
        super(strings.localize("geotype-0", "Lakes"), strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
        
        initUI(lakes);
    }
    
    private void initUI(Vector lakes) {
        Lake lake;
        for (int i = 0; i < lakes.size(); i++) {
            lake = (Lake)lakes.elementAt(i);
            this.append(lake.getName(), null);
        }
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == SELECT_COMMAND) {
            getListener().changeView(MainController.LAKE_CHOISE_LIST_VIEW);
        }
        if (command == back) {
            getListener().back();
        }      
    }
}
