/*
 * TestPage.java
 *
 * Created on 13. duben 2013, 9:55
 */

package cz.muni.fi.wicket;           

//import fi.muni.cz.hibernate.MembersHibMan;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

public class TestPage extends BasePage {

    private String text = "text";
    
    private int counter = 0;

    public TestPage() {
        //<h1>
        add(new Label("message", "My first Hello, World!"));
        
        //link to click
        add(new Link("link") {

            //event after onClick
            @Override
            public void onClick() {
                counter++;
            }
        });  
        
        add(new Label("label", new PropertyModel(this, "counter")));
        
        TestPage.MyForm myForm = new TestPage.MyForm("form");
        add(myForm);
        
        add(new Label("div", new PropertyModel(this, "text")));
        
        Model refreshedTime = new Model() {
            
            @Override
            public String getObject() {
                SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss");
                String time = df.format(new Date());
                return time;
            }            
        };  
        
        add(new Label("refshedtime", refreshedTime));
        add(new Link("refresh") {public void onClick() {}});
        
    }

    private class MyForm extends Form {
        
        private TextField input;
        private TextField label;

        public MyForm(String id) {
            super(id);
            input = new TextField("input", new Model());
            label = new TextField("label2", new Model());
            add(input);
            add(label);
        }
        
        @Override
        protected void onSubmit() {
            /*
            MembersHibMan manager = new MembersHibMan();
            manager.add((String)input.getModelObject());
            label.setModelObject((String)manager.getName());
            manager.getAllNames();
            */
        } 
        
        
    }

}
