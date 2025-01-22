package net.engineeringdigest.journalApp2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailService emailService;

    @Test
    public void testEmailService() {
        emailService.sendEmail("camouflagetr1217@gmail.com","Testing java mail sender","Hi, aap kaise hain ?");
    }
}
