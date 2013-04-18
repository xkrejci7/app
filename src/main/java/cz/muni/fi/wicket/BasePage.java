/*
 * WicketExamplePage.java
 *
 * Created on 13. duben 2013, 9:55
 */
 
package cz.muni.fi.wicket;           

import org.apache.wicket.markup.html.WebPage;

/** 
 *
 * @author Franta
 * @version 
 */

public abstract class BasePage extends WebPage {

    public BasePage() { 
        super(); 
        
        HeaderPanel hp = new HeaderPanel("header");
        hp.addTitle();
        add(hp);
        
        MenuPanel mp = new MenuPanel("menu");
        add(mp);
        
        FooterPanel fp = new FooterPanel("footer");
        fp.addFooter();
        add(fp);

    } 

}