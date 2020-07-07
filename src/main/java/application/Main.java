package application;

import sender.Sender;

public class Main {

    public static void main(String[] args) {
        final Sender sender = new Sender();

        sender.addPropertiesOfEmailService();
        sender.addText();
        sender.addImage();
        sender.sendHtmlFile();
        sender.sendEmail();

    }
}
