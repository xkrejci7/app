/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Franta
 */
public class DTDProcessor implements java.io.Serializable {

    private String XML;
    private File DTD;
    private String message = "";

    public DTDProcessor() {
        XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<bookshell>\n"
                + "    <books year=\"2000\">\n"
                + "        <book>\n"
                + "            <author>J. K. Rowling</author>\n"
                + "            <title>Harry Potter</title>\n"
                + "            <pages>1000</pages>\n"
                + "        </book>\n"
                + "        <book>\n"
                + "            <author>Neznamy</author>\n"
                + "            <title>Nechtel uvest</title>\n"
                + "            <pages>150</pages>\n"
                + "        </book>\n"
                + "    </books>\n"
                + "    <books year=\"1980\">\n"
                + "        <book>\n"
                + "            <author>O. G. Wells</author>\n"
                + "            <title>War of the worlds</title>\n"
                + "            <pages>876</pages>\n"
                + "        </book>\n"
                + "    </books>\n"
                + "    <books year=\"1960\">\n"
                + "        <book>\n"
                + "            <author>O. Sekora</author>\n"
                + "            <title>Ferda Mravenec</title>\n"
                + "            <pages>178</pages>\n"
                + "        </book>\n"
                + "        <book>\n"
                + "            <author>O. Sekora</author>\n"
                + "            <author>O. Sekora</author>\n"
                + "            <title>Dobrodruzstvi brouka Pytlika</title>\n"
                + "            <pages>205</pages>\n"
                + "        </book>\n"
                + "    </books>\n"
                + "</bookshell>";

        DTD = new File("tempDTD.dtd");

        try {

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trace = tf.newTransformer();

            Source source = new StreamSource(new StringReader((XML)));
            StreamResult result = new StreamResult(new StringWriter());

            trace.setParameter(OutputKeys.INDENT, "yes");
            trace.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, DTD.getAbsolutePath());

            trace.transform(source, result);

            XML = result.getWriter().toString();

        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public File getDTD() {
        return DTD;
    }

    public void setDTD(String param) throws IOException {

        if (param == null) {
            param = "";
        }

        BufferedWriter bf = new BufferedWriter(new FileWriter(DTD));
        bf.write(param);
        bf.close();

        //System.out.println(param);

    }

    public void setXML(String XML) {
        this.XML = XML;
    }

    public String getXML() {
        return XML;
    }

    public String getMessage() {
        return message;
    }

    private void setMessage(String Message) {
        this.message = Message;
    }

    private void clearMessage() {
        message = "";
    }

    public void validate() {

        try {

            clearMessage();

            DOMParser parser = new DOMParser();
            parser.setErrorHandler(new ErrorHandler(this));

            parser.setDTDSource(null);
            parser.setFeature("http://xml.org/sax/features/validation", true);

            parser.parse(new InputSource(new StringReader(XML)));

        } catch (SAXNotRecognizedException ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXNotSupportedException ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXParseException ex) {
            //Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getSolution() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<!ELEMENT bookshell (books)+>\n"
                + "\n"
                + "<!ELEMENT books (book)+>\n"
                + "<!ATTLIST books year CDATA #REQUIRED>\n"
                + "\n"
                + "<!ELEMENT book (author+,title,pages)>\n"
                + "\n"
                + "<!ELEMENT author (#PCDATA)>\n"
                + "<!ELEMENT title (#PCDATA)>\n"
                + "<!ELEMENT pages (#PCDATA)>";
    }

    private static class ErrorHandler extends DefaultHandler {

        DTDProcessor DTDprocessor;

        public ErrorHandler(DTDProcessor DTDprocessor) {
            this.DTDprocessor = DTDprocessor;
        }

        @Override
        public void warning(SAXParseException e) {
            DTDprocessor.setMessage(DTDprocessor.getMessage() + "Warning: " + e.getMessage() + "\n");
        }

        @Override
        public void error(SAXParseException e) {
            DTDprocessor.setMessage(DTDprocessor.getMessage() + "Error: " + e.getMessage() + "\n");
        }

        @Override
        public void fatalError(SAXParseException e) {
            DTDprocessor.setMessage(DTDprocessor.getMessage() + "Fatal Error: " + e.getMessage() + "\n");
        }
    }
}
