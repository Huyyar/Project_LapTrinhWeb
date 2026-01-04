package vn.edu.hcmuaf.fit.project_ltweb.services;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class MailService {

    private static final String FROM_EMAIL = "Aosid19999@gmail.com";
    private static final String PASSWORD = "bcqiadbuhvddcuxw";

    public static void sendMail(String toEmail, String content) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(toEmail)
            );
            message.setSubject("Phản hồi từ quản trị viên của trang SnackHub");

            message.setText(content);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
