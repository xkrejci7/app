/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.exercise;

import cz.muni.fi.app.XMLPrinter;
import cz.muni.fi.app.XSLProcessor;
import cz.muni.fi.wicket.BasePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.pretty.Printer;

/**
 *
 * @author Franta
 */
public final class XSL extends BasePage implements java.io.Serializable {

    private String task;
    private String result;
    XSLProcessor xSLProcessor;

    public XSL() {
        super();

        xSLProcessor = new XSLProcessor();
        XMLPrinter printer = new XMLPrinter();

        task = "<p><b>Pro níže uvedené XML dopište XSL Transformaci, která ze zadaného XML vybere všechny knihy "
                + "(element <book>). Počet stránek (element <pages>) ve výsledném XML nebude zobrazen.</b></p><p>" + printer.getEscapedXML(xSLProcessor.getXml()) + "</p>";

        Label taskLabel = new Label("task", new PropertyModel(this, "task"));
        taskLabel.setEscapeModelStrings(false);
        add(taskLabel);

        XSLForm form = new XSLForm("form");

        add(form);
        
        PropertyModel solutionModel = new PropertyModel(this, "solution") {
            
            @Override
            public Object getObject() {
                XMLPrinter printer = new XMLPrinter();
                return (String)printer.getEscapedXML(xSLProcessor.getSolution());
            }
            
        };
        Label solutionLabel = new Label("solutionLabel", solutionModel);
        solutionLabel.setEscapeModelStrings(false);
        add(solutionLabel);

    }

    private class XSLForm extends Form {

        private TextArea inputXSL;
        private Label resultLabel;
        private String result = "";

        public XSLForm(String id) {
            super(id);

            inputXSL = new TextArea("input", new Model());
            inputXSL.setModelObject((String) "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n"
                    + "<xsl:stylesheet version=\"1.0\"\n"
                    + "xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\">\n"
                    + "\n"
                    + "    <xsl:template match=\"/\">\n"
                    + "        <myFavoriteBooks>\n"
                    + "           <xsl:for-each select=\"bookshell/book\">\n"
                    + "               <book>\n"
                    + "                   <author><xsl:value-of select=\"author\"/></author>\n"
                    + "               </book>\n"
                    + "           </xsl:for-each>\n"
                    + "        </myFavoriteBooks>\n"
                    + "    </xsl:template>\n"
                    + "<xsl:output method=\"xml\" encoding=\"utf-8\"  />\n"
                    + "</xsl:stylesheet>");

            PropertyModel pm = new PropertyModel(this, "result") {
                @Override
                public Object getObject() {
                    return result;
                }
            };
            add(inputXSL);

            resultLabel = new Label("resultLabel", pm);
            resultLabel.setEscapeModelStrings(false);
            add(resultLabel);

        }

        @Override
        protected void onSubmit() {

            xSLProcessor.setXsl((String) inputXSL.getModelObject());

            xSLProcessor.validate();

            XMLPrinter printer = new XMLPrinter();

            result = printer.getEscapedXML(xSLProcessor.getMessage());
            
            if (xSLProcessor.getMessage().replaceAll("\\s", "").equals(xSLProcessor.getExpextedResult().replaceAll("\\s", ""))) {
                System.out.println("Vsechno správně!");
                
                result += "<p><b>Transformace se shoduje s očekáváným výsledekm.</b></p>";
            } else {
                result += "<p><b>Transformace se NEshoduje s očekáváným výsledekm!</b></p>";
            }

        }
    }
}
