/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.app;

/**
 *
 * @author Franta
 */
public class Tester {

    public static void main(String[] args) {

        String inputXSL = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" + 
"<xsl:stylesheet version=\"1.0\"\n" + 
"xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n" + 
"\n" + 
"    <xsl:template match=\"/\">\n" + 
"        <myFavoriteBooks>\n" + 
"           <xsl:for-each select=\"bookshell/book\">\n" + 
"               <book>\n" + 
"                   <author><xsl:value-of select=\"author\"/></author>\n" + 
"                   <title><xsl:valsue-of select=\"title\"/></title>\n" + 
"               </book>\n" + 
"           </xsl:for-each>\n" + 
"        </myFavoriteBooks>\n" +                 
"    </xsl:template>\n" + 
"<xsl:output method=\"xml\" encoding=\"utf-8\"  />"                +
"</xsl:stylesheet>";
        

        XSLProcessor proc = new XSLProcessor();
        
        proc.setXsl(inputXSL);
        
        proc.validate();
        
        System.out.println(proc.getMessage());

    }

}
