package application;

import org.xml.sax.SAXException;
import sender.Sender;

import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws MessagingException, IOException, ParserConfigurationException, SAXException {
        //System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        Sender sender = new Sender();
        sender.addPropertiesOfEmailService();
        sender.addText();
        sender.addImage();
        sender.sendEmail();
    }
}
