package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import org.fruct.oss.kareliafishing.Localization;
import org.fruct.oss.kareliafishing.models.DataModel;

/**
 *
 * @author Nikita Davydovskii
 * date: 27.08.2013
 * This class is a view which contains list of available geo object's types. By 
 * clicking on the specific type user can filter map marker's types. This means that 
 * on the map will be only types of markers which user chose.
 */
public class ObjectTypeSelectorView extends List implements CommandListener {
    
    private final ObjectTypeSelectorView.Listener listener;
    
    private Command back;
    
    public ObjectTypeSelectorView(Localization strings, DataModel model,
            ObjectTypeSelectorView.Listener listener) {
        super(strings.localize("selectorTitle", "Select type"), Choice.IMPLICIT);
        this.listener = listener;
        
        back = new Command(strings.localize("back", "Back"), Command.BACK, 0);
        this.addCommand(back);
        
        this.setCommandListener(this);
        
        this.append(strings.localize("all", "All"), null);
        for (int i = 0; i < model.getGeoTypesList().length; i++) {
            this.append(model.getGeoTypesList()[i], null);
        }
    }  

    public void commandAction(Command command, Displayable displayable) {
        if (command == SELECT_COMMAND) {
            listener.chosenType(this.getSelectedIndex());
        }
        if (command == back) {
            listener.back();
        }
    }
    
    public interface Listener {
        void chosenType(int type);
        
        void back();
    }
    
}
