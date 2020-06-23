package com.shinn;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailTest {

    public static void main(String [] args) {
        // Recipient's email ID needs to be mentioned.
        String to = "shinnpoy@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "donotreply@caburnaybrokerage.com";

        // Assuming you are sending email from localhost
        String host = "xjdz2.dailyrazor.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", "xjdz2.dailyrazor.com");
        properties.setProperty("mail.user", "caburnay");
        properties.setProperty("mail.password", "[FK1Z.uuX260jm");
        properties.setProperty("mail.port", "465");
//        #properties.setProperty("mail.smtp.auth","true");
//        #properties.setProperty("mail.smtp.starttls.enable=","true");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("This is the Subject Line!");

            // Now set the actual message
            message.setText("This is actual message");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
