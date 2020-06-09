package sender;

import org.xml.sax.SAXException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.*;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Sender {

    private Properties prop;
    private Message message;
    private Session session;
    private Multipart multipart;

    public void addPropertiesOfEmailService() {
        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        //prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("seregaserver322@gmail.com", "qwertyuiop[]37");
            }
        });
    }

    public void addText() throws MessagingException {
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress("seregaserver322@gmail.com"));
        message.setRecipients(
            Message.RecipientType.TO, InternetAddress.parse("serya42572@gmail.com"));
        message.setSubject("Mail Subject");

        String msg = "This is my 7 email using JavaMailer";

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");

        multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);
    }

    public void addImage() throws IOException, MessagingException, SAXException, ParserConfigurationException {

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        attachmentBodyPart.attachFile(new File(classLoader.getResource("/main/resources/image/im.jpg").getFile()));

        multipart.addBodyPart(attachmentBodyPart);
        message.setContent(multipart);

    }
    public void sendEmail() throws MessagingException {
        Transport.send(message);
    }

    public void sendHtmlFile() {

    }
}
