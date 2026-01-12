package net.exploringspringboot.journalApp.controller;
//controller is special type of classes or special type of comaponate beacasue been tho ban he jaai ge
import net.exploringspringboot.journalApp.Entity.JournalEntry;
import net.exploringspringboot.journalApp.Entity.User;
import net.exploringspringboot.journalApp.Repository.UserEntryRepository;
import net.exploringspringboot.journalApp.Service.JournalEntryService;
import net.exploringspringboot.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/Journal")
public class JournalEntryController_v2 {
    @Autowired
    private JournalEntryService journalEntryService;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntryService.saveEntry(myEntry);
//        return true;
//    }

    @PostMapping
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry) {
       try {
           Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
           String username = authentication.getName();
           journalEntryService.saveEntry(myEntry,username);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
       }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }
    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List< JournalEntry>collect= user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> JournalEntry = journalEntryService.findById(myId);
            if(JournalEntry.isPresent() ){
                return new ResponseEntity<>(JournalEntry.get(),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        boolean removed = journalEntryService.deleteById(myId, username);
        if (removed) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("id/{myId}")
    public ResponseEntity<?> updateJourenalById
            (@PathVariable ObjectId myId,
             @RequestBody JournalEntry newEntry
             ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        List< JournalEntry>collect= user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> JournalEntry = journalEntryService.findById(myId);
            if(JournalEntry.isPresent() ){
                JournalEntry old = JournalEntry.get();
                old.setTitle(newEntry.getTitle() !=null && ! newEntry.getTitle().equals(" ")? newEntry.getTitle(): old.getTitle());
                    old.setContent(newEntry.getContent()!=null && ! newEntry.getContent().equals(" ")? newEntry.getContent(): old.getContent());
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old,HttpStatus.OK);
            }
        }



        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}


