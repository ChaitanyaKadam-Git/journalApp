package net.exploringspringboot.journalApp.controller;

import net.exploringspringboot.journalApp.Entity.User;
import net.exploringspringboot.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.saveNewUser(user);
        return "User Registered Successfully!";
    }
}

