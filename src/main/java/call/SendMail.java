/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package call;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author HP
 */
public class SendMail {
    
      
   
    
public void sendMailTLS(MyNewMessage myNewMessage){
        
                // Recipient's email ID needs to be mentioned.
        String to = myNewMessage.getToTeamMember();

        // Sender's email ID needs to be mentioned
        String from = "info@jmdev.cba.pl";
        final String username = "info@ktosdzwonil.com";//change accordingly
        final String password = "12345";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        String host = "localhost";

//        Properties props = new Properties();
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");

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
            message.setSubject(myNewMessage.getPrefix() + myNewMessage.getCustomer() + " " + myNewMessage.getProducts());

            // Now set the actual message
            message.setContent(
                    
                    "<strong>Zarejestrowano nowa rozmowe: </strong><br>" +
                    "<h2>DANE KLIENTA:</h2>" + "<br>" +
                    "Imie i nazwisko: " + myNewMessage.getCustomer() + "<br>" + 
                    "Firma: " + myNewMessage.getCompany() + "<br>" +
                    "<br>" +
                    "Nr tel.: " + myNewMessage.getPhone() + "<br>" +
                    "Email: " + myNewMessage.getEmail() + "<br>" +
                                                 
                    "<h3>W sprawie: </h3>" + myNewMessage.getProducts() + "<br>" +
                            
                    "<br><br>" +
                    "Szczególy zapisane podczas rozmowy: \n" + "<br>" +                                 
                    myNewMessage.getDetails() + "<br>" +
                    
                    "<br><br>" + 
                    "----------------------------------------------<br>" +        
                    "-> Program Ktos dzwonil! v.1.0 <br>" +
                    "www.jmdev.cba.pl"
            
            , "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    
    
}
