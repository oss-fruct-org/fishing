package org.fruct.oss.kareliafishing.views;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.MainController;
import org.fruct.oss.kareliafishing.models.Shop;

/**
 *
 * @author Nikita Davydovskii
 * date: 25.07.13
 * This view contains list of all shops.
 */
public class ShopsListView extends BaseListView implements CommandListener {
    
    private Command back;
    
    public ShopsListView(Localization strings, Vector shops, Listener listener) {
        super(strings.localize("geotype-1", "Shops"), strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
        
        initUI(shops);
    }
    
    private void initUI(Vector shops) {
        Shop shop;
        for (int i = 0; i < shops.size(); i++) {
            shop = (Shop)shops.elementAt(i);
            this.append(shop.getName(), null);
        }
    }

    public void commandAction(Command command, Displayable displayable) {
        if (command == SELECT_COMMAND) {
            getListener().changeView(MainController.SHOP_CHOISE_LIST_VIEW);
        }
        if (command == back) {
            getListener().changeView(MainController.CATEGORY_LIST_VIEW);
        }
    }
    
    
    
}
