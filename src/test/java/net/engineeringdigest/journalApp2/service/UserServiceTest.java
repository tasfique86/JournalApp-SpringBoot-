package net.engineeringdigest.journalApp2.service;

import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PutMapping;
@Disabled
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;



    @Disabled
    @Test
    public void testFindByUserName() {
        User user = userRepository.findByUserName("tas");
        Assertions.assertTrue(!user.getJournalEntries().isEmpty());
       // Assertions.assertEquals(4,8 +2);
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9"
    })
    public void test(int a, int b, int c) {
        Assertions.assertEquals(c, a+b);
    }

}
