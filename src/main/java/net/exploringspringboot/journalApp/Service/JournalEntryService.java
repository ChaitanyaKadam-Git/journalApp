package net.exploringspringboot.journalApp.Service;

import net.exploringspringboot.journalApp.Entity.JournalEntry;
import net.exploringspringboot.journalApp.Entity.User;
import net.exploringspringboot.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;



    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username){
        try {
            User user = userService.findByUsername(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);

        }catch (Exception e){
            log.error("Exception", e);
            throw new RuntimeException("An error occuerd while saving this entry "+ e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);

    }


    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);

    }
    @Transactional
    public  boolean deleteById(ObjectId id , String username) {
        boolean removed = false;
        try {
            User user = userService.findByUsername(username);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);

            }


        }
        catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry " + e);
        }
        return removed;
    }
}

