/*
 * FooterPanel.java
 *
 * Created on 13. duben 2013, 9:55
 */
 
package cz.muni.fi.wicket;           

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

/** 
 *
 * @author Franta
 * @version 
 */

public final class FooterPanel extends Panel {
    
    private String footerText = "xkrejci7 - Powered by Wicket & Maven & Hibernate and the NetBeans Wicket Plugin";

    public FooterPanel(String componentName) {
        super(componentName);
    }

    public String getFooterText() {
        return footerText;
    }

    public void setFooterText(String footerText) {
        this.footerText = footerText;
    }
    
    public void addFooter() {
        addFooter(getFooterText());
    }
    public void addFooter(String title) {
        setFooterText(title);
        add(new Label("footer", getFooterText()));
    }

}
