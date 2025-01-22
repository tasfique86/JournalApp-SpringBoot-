package net.engineeringdigest.journalApp2.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserSchedulerTest {

    @Autowired
    private UserSchduler userScheduler;

    @Test
    public void testUserScheduler() {
        userScheduler.fetchUserAndSendMail();
    }
}
