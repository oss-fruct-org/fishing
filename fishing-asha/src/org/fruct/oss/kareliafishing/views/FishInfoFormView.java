package org.fruct.oss.kareliafishing.views;

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
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
    private Image noImageAvailable;

    public FishInfoFormView(Localization strings, Listener listener) {
        super(null, strings, listener);
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
        
        try {
            noImageAvailable = Image.createImage("/no-image-available-rectangle-" + 
                    strings.getLocale() + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setInfo(Fish fish) {
        // set image
        if (fish.getPicture() != null) {
            this.append(fish.getPicture());
        } else {
            this.append(noImageAvailable);
        }
        
        // set text
        if (!fish.getFishInfo().equals("")) {
            this.append(fish.getFishInfo());
        } else {
            this.append(getStrings().localize("noinfoava", "No information available"));
        }     
    }
    
    
    
    public void commandAction(Command command, Displayable displayable) {
        if (command == back) {
            getListener().back();
        }
    }
    
}
