/*
 * Application.java
 *
 * Created on 13. duben 2013, 9:55
 */
 
package cz.muni.fi.wicket;           

import org.apache.wicket.protocol.http.WebApplication;
/** 
 *
 * @author Franta
 * @version 
 */

public class Application extends WebApplication {

    public Application() {
    }

    public Class getHomePage() {
        return HomePage.class;
    }

}
