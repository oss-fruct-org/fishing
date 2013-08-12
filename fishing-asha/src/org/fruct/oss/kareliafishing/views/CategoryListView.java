package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.models.DataModel;

/**
 *
 * @author Nikita Davydovskii
 * date: 25.07.13
 * This view contains list of all types of objects.
 */
public class CategoryListView extends BaseListView implements CommandListener{
       
    public static final int LAKE_ITEM = 0;
    public static final int SHOP_ITEM = 1;
    public static final int HOSTEL_ITEM = 2;
    
    private Command about;
            
    public CategoryListView(Localization strings, DataModel model, 
            BaseListView.Listener listener) {
        super(strings.localize("maintitle", "Karelia Fishing"), strings, listener);
        
        about = new Command(strings.localize("about", "About"), Command.SCREEN, 1);
        this.addCommand(about);
        
        this.setCommandListener(this);

        initUI(model);
    }
    
    private void initUI(DataModel model) {
        for (int i = 0; i < model.getGeoTypesList().length; i++) {
            this.append(model.getGeoTypesList()[i], null);
        }   
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == SELECT_COMMAND) {
            switch (this.getSelectedIndex()) {
                case LAKE_ITEM:
                    getListener().changeView(MainController.LAKES_LIST_VIEW);
                    break;
                case SHOP_ITEM:
                    getListener().changeView(MainController.SHOPS_LIST_VIEW);
                    break;
                case HOSTEL_ITEM:
                    getListener().changeView(MainController.HOSTELS_LIST_VIEW);
                    break;
                default:
                    System.out.println("Index was not recognized");
            }
        } else if (command == about) {
            getListener().changeView(MainController.ABOUT_VIEW);
        }
    }
}
