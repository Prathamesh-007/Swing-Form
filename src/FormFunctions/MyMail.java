package FormFunctions;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class MyMail {

    Session newSession = null;
    MimeMessage mimeMessage = null;
    public static void sendEmail(String toUser, String emailSubject, String emailBody) throws MessagingException {
        MyMail mail = new MyMail();
        mail.setupServerProperties();
        mail.draftEmail(toUser, emailSubject, emailBody);
        mail.send();

    }

    private void send() throws MessagingException {
        // Enter fromUser email and respective password
        String fromUser = "";
        String password = "";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp");
        transport.connect(emailHost, fromUser, password);
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
        System.out.println("Email sent successfully!");

    }

    private void draftEmail(String toUser, String emailSubject, String emailBody) throws MessagingException {

        mimeMessage = new MimeMessage(newSession);

        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));

        mimeMessage.setSubject(emailSubject);


        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "text/html;charset=UTF-8");
        MimeMultipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        mimeMessage.setContent(multipart);

    }

    private void setupServerProperties() {
        Properties properties = System.getProperties();
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        newSession = Session.getDefaultInstance(properties, null);
    }
}
