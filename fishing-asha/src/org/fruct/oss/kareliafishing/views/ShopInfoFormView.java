package org.fruct.oss.kareliafishing.views;

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
 * This view contains all information that available for the given shop.
 */
public class ShopInfoFormView extends BaseFormView implements CommandListener {
    
    private Command back;
   
    public ShopInfoFormView(Localization strings, Listener listener) {
        super(strings.localize("infoitem", "Information"), strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);    
    }
    
    public void setInfo(Shop shop) {
        this.append(shop.getName() + "\n");
        this.append(getStrings().localize("coords", "Coordinates") + "\n" + 
                shop.getLatitude() + ", " + shop.getLongitude() + "\n");
        this.append(getStrings().localize("address", "Address") + "\n" + 
                shop.getAddress() + "\n");
        this.append(getStrings().localize("tel", "Telephone") + "\n" + 
                shop.getPhone());       
    }
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
            getListener().back();
        }
    }
}
