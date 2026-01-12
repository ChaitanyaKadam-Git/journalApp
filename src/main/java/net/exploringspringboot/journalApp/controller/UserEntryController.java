package net.exploringspringboot.journalApp.controller;
//controller is special type of classes or special type of comaponate beacasue been tho ban he jaai ge

import net.exploringspringboot.journalApp.Entity.User;
import net.exploringspringboot.journalApp.Repository.UserEntryRepository;
import net.exploringspringboot.journalApp.Service.UserService;
import net.exploringspringboot.journalApp.Service.WeatherService;
import net.exploringspringboot.journalApp.api.respones.WeatherResoponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private WeatherService weatherService ;


    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveNewUser(user);
    }
//    @GetMapping
//    public List<User>getAllUsers(){
//        return userService.getAllEntries();
//    }


    @PutMapping()
    public ResponseEntity<?>updateUser(@RequestBody User user ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);
        userInDb.setUsername(user.getUsername());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?>deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        userEntryRepository.deleteByUsername(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?>greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResoponse weatherResoponse = weatherService.Getweather("Pune");
        String greeting = " ";
        if (weatherResoponse != null){
           greeting = " ,Weather feels like "+weatherResoponse.getCurrent().getFeelslike();
        }

            return new ResponseEntity<>("Hi" + "  " + authentication.getName()+greeting,HttpStatus.OK);
    }


//    @PutMapping({"/{userName}"})
//    public ResponseEntity<?>updateUser(@RequestBody User user , @PathVariable String userName){
//        User userInDb = userService.findByUsername(userName);
//        if(userInDb !=null){
//            userInDb.setUsername(user.getUsername());
//            userInDb.setPassword(user.getPassword());
//            userService.saveEntry(userInDb);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }


}


