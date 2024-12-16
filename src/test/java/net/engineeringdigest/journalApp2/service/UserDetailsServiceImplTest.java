package net.engineeringdigest.journalApp2.service;

import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTest {

   @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("tas").password("sfj;asf").roles(new ArrayList<>()).build());
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("tas");
        Assertions.assertNotNull(userDetails);
    }
}
