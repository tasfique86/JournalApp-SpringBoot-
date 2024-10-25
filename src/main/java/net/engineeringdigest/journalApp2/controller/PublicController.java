package net.engineeringdigest.journalApp2.controller;

import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @GetMapping("/health-check")
    public String healthCheck() {
        return "Ok";
    }

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

//    @PostMapping("/create-user")
//    public void createUser(@RequestBody User user) {
//        userService.saveNewUser(user);
//    }
}
