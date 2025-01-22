package net.engineeringdigest.journalApp2.controller;


import net.engineeringdigest.journalApp2.cache.AppCache;
import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;



    @GetMapping("all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.findAll();
        if(users!=null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create-admin-user")
    public void createAdminUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    public void clearAppCache(){
        appCache.init();
    }
}
