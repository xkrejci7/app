/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.app;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author Franta
 */
public class SchemaProcessor implements java.io.Serializable {

    private String XML;
    private String XSD;
    private String message = "";

    public SchemaProcessor() {

        XML = "<?xml version=\"1.0\"?>\n"
                + "<note>\n"
                + "  <to>Tove</to>\n"
                + "  <from>Jani</from>\n"
                + "  <heading>Reminder</heading>\n"
                + "  <body>Don't forget me this weekend!</body>\n"
                + "</note>\n";

    }

    public void validate() {

        clearMessage();

        try {

            // define the type of schema - we use W3C:
            String schemaLang = "http://www.w3.org/2001/XMLSchema";

            // get validation driver:
            SchemaFactory factory = SchemaFactory.newInstance(schemaLang);

            // create schema by reading it from an XSD file:
            javax.xml.validation.Schema schema = factory.newSchema(new StreamSource(new StringReader(XSD)));
            Validator validator = schema.newValidator();

            // at last perform validation:
            validator.validate(new StreamSource(new StringReader(XML)));

        } catch (SAXException ex) {

            message = ex.getMessage().toString();

        } catch (IOException ex) {
            Logger.getLogger(SchemaProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getXML() {
        return XML;
    }

    public String getMessage() {
        return message;
    }

    public void setXSD(String XSD) {
        this.XSD = XSD;
    }

    private void clearMessage() {
        message = "";
    }

    public String getSolution() {
        return "<?xml version=\"1.0\"?>\n"
                + "<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "elementFormDefault=\"qualified\">\n\n"
                + "<xsd:element name=\"note\">\n"
                + "  <xsd:complexType>\n"
                + "    <xsd:sequence>\n"
                + "      <xsd:element name=\"to\" type=\"xsd:string\"/>\n"
                + "      <xsd:element name=\"from\" type=\"xsd:string\"/>\n"
                + "      <xsd:element name=\"heading\" type=\"xsd:string\"/>\n"
                + "      <xsd:element name=\"body\" type=\"xsd:string\"/>\n"
                + "    </xsd:sequence>\n"
                + "  </xsd:complexType>\n"
                + "</xsd:element>\n\n"
                + "</xsd:schema>";       
    }
}
