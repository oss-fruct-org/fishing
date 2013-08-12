package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;
import org.fruct.oss.kareliafishing.Localization;

/**
 *
 * @author Nikita Davydovskii
 * date: 25.07.2013
 * This class is a base class for all lists which will be used in this 
 * application. It provides an interface through which data will be exchange 
 * with main controller. 
 */
public class BaseListView extends List {
    
    private final BaseListView.Listener listener;
    private Localization strings;
    
    public BaseListView(String title, Localization strings, BaseListView.Listener listener) {
        super(title, Choice.IMPLICIT);
        this.strings = strings;
        this.listener = listener;
    }
  
    public Localization getStrings() {
        return strings;
    }

    public BaseListView.Listener getListener() {
        return listener;
    }
    
    public interface Listener {
        void changeView(int view);
    }
}
