/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.dragoncoffee.email;

import java.util.*;
import javax.ejb.*;
import javax.mail.*;
import javax.mail.internet.*;
/**
 * this method is used to send email to user
 * @ref: https://www.tutorialspoint.com/javamail_api/javamail_api_gmail_smtp_server.htm
 * @author Jianhao_LI
 */
@Stateless
@LocalBean
public class EmailSessionBean {

    public void sendEmail(String to, String subject, String body){
        // Recipient's email ID needs to be mentioned.
//        String to = "xyz@gmail.com";//change accordingly

        // Sender's email ID needs to be mentioned
        String from = "aipdragoncoffe@gmail.com";//change accordingly
        final String username = "aipdragoncoffee@gmail.com";//change accordingly
        final String password = "dragoncoffee";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject(subject);

            // Now set the actual message
            message.setText(body);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
