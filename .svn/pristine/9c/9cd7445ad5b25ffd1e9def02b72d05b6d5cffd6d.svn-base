/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.app;

import org.apache.commons.lang3.StringEscapeUtils;


/**
 *
 * @author Franta
 */
public class XMLPrinter {
    
    public XMLPrinter() {
    }
    
    public String getEscapedXML(String xmlToPrint) {
        
        String tmp = StringEscapeUtils.escapeXml(xmlToPrint);
        tmp = tmp.replaceAll(" ", "&nbsp;");
        tmp = tmp.replaceAll("\n", "<br />");
        
        return tmp;
        
    }
    
}
