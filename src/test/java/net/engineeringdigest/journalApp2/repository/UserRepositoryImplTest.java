package net.engineeringdigest.journalApp2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void test() {
            userRepository.getUserForSA();
    }
}
