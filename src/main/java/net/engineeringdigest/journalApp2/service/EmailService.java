package net.engineeringdigest.journalApp2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Slf4j

public class EmailService {

    //@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String body) {
        try{
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(to);
            mail.setSubject(subject);
            mail.setText(body);
            mail.setFrom("tasfiquerishad1217@gmail.com");
            javaMailSender.send(mail);
        }catch (Exception e) {
         log.error("Exception while sending email", e);
        }
    }
}
