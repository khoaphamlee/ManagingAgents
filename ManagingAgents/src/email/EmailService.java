package email;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService implements IJavaMail {

	@Override
	public boolean send(String to, String subject, String message) {
		// Get properties object
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", EmailProperty.HOST_NAME);
        props.put("mail.smtp.socketFactory.port",EmailProperty.SSL_PORT);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.port", EmailProperty.SSL_PORT);
 
        // get Session
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EmailProperty.APP_EMAIL, EmailProperty.APP_PASSWORD);
            }
        });
 
        // compose message
        try {
            MimeMessage message2 = new MimeMessage(session);
            message2.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message2.setSubject(subject);
            message2.setText(message);
            
            // send message
            Transport.send(message2);
            
 
            System.out.println("Message sent successfully");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
		
	}
	
	

}
