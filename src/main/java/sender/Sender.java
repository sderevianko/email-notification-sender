package sender;

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
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sender {

    private Message message;
    private Multipart multipart;
    private static final Logger logger = Logger.getLogger(Sender.class.getName());

    public void addPropertiesOfEmailService() {
        final Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.auth", "true");

        final Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("seregaserver322@gmail.com", "*****");
            }
        });
        message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("seregaserver322@gmail.com"));
            message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse("serya42572@gmail.com"));
            multipart = new MimeMultipart();
        } catch (MessagingException messagingException) {
            logger.log(Level.SEVERE, "Internet address not found", messagingException);
        }
    }

    public void addText() {
        try { message.setSubject("News");
        final String msg = "News from NASA";
        final MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(msg, "text/html");
        multipart.addBodyPart(mimeBodyPart);
        } catch (MessagingException messagingException) {
            logger.log(Level.SEVERE, "Message not found", messagingException);
        }
    }

    public void addImage() {
        final MimeBodyPart attachmentBodyPartImage = new MimeBodyPart();
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            try {
                attachmentBodyPartImage.attachFile(new File(classLoader.getResource("image/im.jpg").getFile()));
                multipart.addBodyPart(attachmentBodyPartImage);
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "Image not found", ioException);
            }
        } catch (MessagingException messagingException) {
            logger.log(Level.SEVERE, "Message not found", messagingException);
        }
    }

    public void sendHtmlFile() {
        final MimeBodyPart attachmentBodyPartHtml = new MimeBodyPart();
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            try {
                attachmentBodyPartHtml.attachFile(new File(classLoader.getResource("templates/test.html").getFile()));
                multipart.addBodyPart(attachmentBodyPartHtml);
            } catch (IOException ioException) {
                logger.log(Level.SEVERE, "File not found", ioException);
            }
        } catch (MessagingException messagingException) {
            logger.log(Level.SEVERE, "Message not found", messagingException);
        }
    }

    public void sendEmail() {
        try {
            message.setContent(multipart);
            Transport.send(message);
        } catch (MessagingException messagingException) {
            logger.log(Level.SEVERE, "Message not found", messagingException);
        }
    }
}
