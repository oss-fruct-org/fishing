package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import org.fruct.oss.kareliafishing.Localization;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.MainController;

/**
 *
 * @author Nikita Davydovskii
 * date: 29.07.2013
 * This view contains list choices according to giving lake. 
 */
public class LakeChoiceListView  extends BaseListView implements CommandListener {
    
    private Command back;
    
    public static final int INFO_ITEM = 0;
    public static final int FISHES_ITEM = 1;
    public static final int MAP_ITEM = 2;

    public LakeChoiceListView(String title, Localization strings, 
            BaseListView.Listener listener) {
        super(title, strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
       
        initUI();
    }
    
    private void initUI() {
        this.append(getStrings().localize("infoitem", "Information"), null);
        this.append(getStrings().localize("fish", "Fish"), null);
        this.append(getStrings().localize("mapitem", "Show on map"), null);
    }
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
            getListener().back();
        }
        if (command == SELECT_COMMAND) {
            switch (this.getSelectedIndex()) {
                case INFO_ITEM:
                    getListener().changeView(MainController.LAKE_INFO_FORM_VIEW);
                    break;
                case FISHES_ITEM:
                    getListener().changeView(MainController.FISHES_LIST_VIEW);
                    break;
                case MAP_ITEM:
                    getListener().changeView(MainController.MAP_VIEW);
                    break;
            }
        }
    }
    
    
    
    

}
