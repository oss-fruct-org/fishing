package org.fruct.oss.kareliafishing.views;

import javax.microedition.lcdui.Form;
import org.fruct.oss.kareliafishing.Localization;

/**
 *
 * @author Nikita Davydovskii
 * date: 22.07.2013
 * This class is a base class for all view which will be used in this 
 * application. It provides an interface through which data will be exchange 
 * with main controller. 
 */
public class BaseFormView extends Form {
    
    private final BaseFormView.Listener listener;
    private Localization strings;

    public BaseFormView(String title, Localization strings, Listener listener) {
        super(title);
        this.strings = strings;
        this.listener = listener;
    }
    
    public Localization getStrings() {
        return strings;
    }

    public Listener getListener() {
        return listener;
    }
    
    public interface Listener {
        void changeView(int view);
    }
}
