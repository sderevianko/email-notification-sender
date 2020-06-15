package application;

import org.xml.sax.SAXException;
import sender.Sender;

import javax.mail.MessagingException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws MessagingException, IOException, ParserConfigurationException, SAXException {
        Sender sender = new Sender();

        sender.addPropertiesOfEmailService();
        sender.addText();
        sender.addImage();
        sender.sendHtmlFile();
        sender.sendEmail();

    }
}
