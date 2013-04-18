/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.app;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author Franta
 */
public class XSLProcessor implements java.io.Serializable {

    private String xml;
    private String xsl;
    private String message;

    public XSLProcessor() {

        xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
                + "<bookshell>\n"
                + "  <book>\n"
                + "    <author>J. K. Rowling</author>\n"
                + "    <title>Harry Potter</title>\n"
                + "    <pages>1000</pages>\n"
                + "  </book>\n"
                + "  <book>\n"
                + "    <author>O. G. Wells</author>\n"
                + "    <title>War of the worlds</title>\n"
                + "    <pages>876</pages>\n"
                + "  </book>\n"
                + "</bookshell> \n";

    }

    public void setXsl(String param) {

        if (param == null) {
            param = "";
        }

        xsl = param;
    }

    private void clearMessage() {
        message = "";
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public void validate() {

        clearMessage();

        MyErrorListener el = new MyErrorListener(this);

        try {

            TransformerFactory factory = TransformerFactory.newInstance();
            factory.setErrorListener(el);

            StreamSource xslStream = new StreamSource(new StringReader(xsl));

            Transformer transformer = factory.newTransformer(xslStream);
            transformer.setErrorListener(el);

            StreamSource in = new StreamSource(new StringReader(xml));
            StreamResult out = new StreamResult(new StringWriter());

            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(in, out);

            message += out.getWriter().toString();

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(XSLProcessor.class.getName()).log(Level.SEVERE, null, ex);
            //message += ex.getMessage();
        } catch (TransformerException ex) {
            Logger.getLogger(XSLProcessor.class.getName()).log(Level.SEVERE, null, ex);
            //message += ex.getMessage();
        }

    }

    public String getExpextedResult() {

        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n"
                + "<myFavoriteBooks>\n"
                + "    <book>\n"
                + "        <author>J. K. Rowling</author>\n"
                + "        <title>Harry Potter</title>\n"
                + "    </book>\n"
                + "    <book>\n"
                + "        <author>O. G. Wells</author>\n"
                + "        <title>War of the worlds</title>\n"
                + "    </book>\n"
                + "</myFavoriteBooks> ";

    }

    public String getSolution() {
        return "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
                + "<xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
                + "\n"
                + "    <xsl:template match=\"/\">\n"
                + "        <myFavoriteBooks>\n"
                + "           <xsl:for-each select=\"bookshell/book\">\n"
                + "               <book>\n"
                + "                   <author><xsl:value-of select=\"author\"/></author>\n"
                + "                   <title><xsl:value-of select=\"title\"/></title>\n"
                + "               </book>\n"
                + "           </xsl:for-each>\n"
                + "        </myFavoriteBooks>\n"
                + "    </xsl:template>\n"
                + "<xsl:output method=\"xml\" encoding=\"utf-8\"  />\n"
                + "</xsl:stylesheet>";
    }

    public class MyErrorListener implements ErrorListener {

        private XSLProcessor xSLProcessor;

        public MyErrorListener(XSLProcessor proc) {
            this.xSLProcessor = proc;
        }

        public void warning(TransformerException e) {
            xSLProcessor.setMessage(xSLProcessor.getMessage() + "Warning: " + e.getMessage() + "\n");

        }

        public void error(TransformerException e)
                throws TransformerException {
            xSLProcessor.setMessage(xSLProcessor.getMessage() + "Error: " + e.getMessage() + "\n");
            throw e;

        }

        public void fatalError(TransformerException e)
                throws TransformerException {
            xSLProcessor.setMessage(xSLProcessor.getMessage() + "Fatal Error: " + e.getMessage() + "\n");
            throw e;

        }
    }
}
