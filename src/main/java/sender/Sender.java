package sender;

import org.xml.sax.SAXException;

import javax.mail.Authenticator;
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
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Sender {

    private Properties prop;
    private Message message;
    private Session session;
    private Multipart multipart;
    private MimeBodyPart attachmentBodyPartImage;
    private MimeBodyPart attachmentBodyPartHtml;

    public void addPropertiesOfEmailService() throws MessagingException {
        prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("seregaserver322@gmail.com", "*****");
            }
        });
        message = new MimeMessage(session);
        message.setFrom(new InternetAddress("seregaserver322@gmail.com"));
        message.setRecipients(
            Message.RecipientType.TO, InternetAddress.parse("serya42572@gmail.com"));
        multipart = new MimeMultipart();
    }

    public void addText() throws MessagingException {
        message.setSubject("Picture");
        String msg = "This is my last email using JavaMailer";
        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");
        multipart.addBodyPart(mimeBodyPart);
    }

    public void addImage() throws IOException, MessagingException {
        attachmentBodyPartImage = new MimeBodyPart();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        attachmentBodyPartImage.attachFile(new File(classLoader.getResource("image/im.jpg").getFile()));
        multipart.addBodyPart(attachmentBodyPartImage);
    }

    public void sendHtmlFile() throws IOException, MessagingException {
        attachmentBodyPartHtml = new MimeBodyPart();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        attachmentBodyPartHtml.attachFile(new File(classLoader.getResource("templates/test.html").getFile()));
        multipart.addBodyPart(attachmentBodyPartHtml);
    }

    public void sendEmail() throws MessagingException {
        message.setContent(multipart);
        Transport.send(message);
    }
}
