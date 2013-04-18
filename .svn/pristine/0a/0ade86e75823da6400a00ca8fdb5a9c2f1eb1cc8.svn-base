/*
 * HeaderPanel.java
 *
 * Created on 13. duben 2013, 9:55
 */
 
package cz.muni.fi.wicket;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author xkrejci7
 * @version 
 */

public class HeaderPanel extends Panel {

    private String title = "Podpůrný nástroj pro výuku značkovacích jazyků";
    
    /**
     * Construct.
     * @param componentName name of the component
     * @param exampleTitle title of the example
     */
    public HeaderPanel(String componentName) {
        super(componentName);
    }    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addTitle() {
        addTitle(getTitle());
    }
    public void addTitle(String title) {
        setTitle(title);
        add(new Label("title", getTitle()));
    }
    

}