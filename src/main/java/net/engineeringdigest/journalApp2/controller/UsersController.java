package net.engineeringdigest.journalApp2.controller;

import net.engineeringdigest.journalApp2.api.response.WeatherResponse;
import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.repository.UserRepository;
import net.engineeringdigest.journalApp2.service.UserService;
import net.engineeringdigest.journalApp2.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
     private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {

       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
       String userName = authentication.getName();

        User user1=userService.findByUsername(userName);
        if(user1==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user1.setUserName(user.getUserName());
        user1.setPassword(user.getPassword());
        userService.saveNewUser(user1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherrRespones= weatherService.getWeather("Dhaka");
        String greeting="";
        if(weatherrRespones!=null) {
            greeting=", Weather feel like "+weatherrRespones.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greeting, HttpStatus.OK);
    }


}
