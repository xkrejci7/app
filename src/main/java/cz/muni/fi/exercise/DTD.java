/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.exercise;


import cz.muni.fi.app.DTDProcessor;
import cz.muni.fi.app.XMLPrinter;
import cz.muni.fi.wicket.BasePage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;


/**
 *
 * @author Franta
 */
public final class DTD extends BasePage implements java.io.Serializable {
    
    private String task;
    private String result;
    DTDProcessor DTDProcessor;

    public DTD() {
        super();
        
        DTDProcessor = new DTDProcessor();
        XMLPrinter printer = new XMLPrinter();
        
        task = "<p><b>Pro níže uvedené XML dopište správné DTD:</b></p><p>" + printer.getEscapedXML(DTDProcessor.getXML()) + "</p>";
        
        Label taskLabel = new Label("task",  new PropertyModel(this, "task"));
        taskLabel.setEscapeModelStrings(false);
        add(taskLabel);
        
        DTDForm form = new DTDForm("form");
       
        add(form);
        
        PropertyModel solutionModel = new PropertyModel(this, "solution") {
            
            @Override
            public Object getObject() {
                XMLPrinter printer = new XMLPrinter();
                return (String)printer.getEscapedXML(DTDProcessor.getSolution());
            }
            
        };
        Label solutionLabel = new Label("solutionLabel", solutionModel);
        solutionLabel.setEscapeModelStrings(false);
        add(solutionLabel);
    }

    private class DTDForm extends Form {
        
        private TextArea inputDTD;
        private Label resultLabel;
        
        private String result="";

        public DTDForm(String id) {
            super(id);
            
            inputDTD = new TextArea("input", new Model());
            inputDTD.setModelObject((String)"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
"<!ELEMENT bookshell (books)+>\n" +
"<!ATTLIST books year CDATA #REQUIRED>\n" +
"<!ELEMENT book (author+,title,pages)>\n" +
"<!ELEMENT author (#PCDATA)>");
            add(inputDTD);
            
            PropertyModel pm = new PropertyModel(this, "result") {

                @Override
                public Object getObject() {
                    return result;
                }
                
            };
            
            resultLabel = new Label("resultLabel", pm);
            resultLabel.setEscapeModelStrings(false);
            add(resultLabel);
            
        }
        
        @Override
        protected void onSubmit() {
            
        try {
           
            DTDProcessor.setDTD((String)inputDTD.getModelObject());
            
            //System.out.println(DTDProcessor.getDTD().getAbsolutePath()+ "__" + DTDProcessor.getDTD().getName());
            
            DTDProcessor.validate();
            
            XMLPrinter printer = new XMLPrinter();
            
            result = printer.getEscapedXML(DTDProcessor.getMessage());
            
            if (result.equals("")) {
                result = "DTD je správně.";
            }
            
        } catch (Exception ex) {
            Logger.getLogger(DTDProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }   
        
    }

    

}