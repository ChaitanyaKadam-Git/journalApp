package net.exploringspringboot.journalApp.Service;

import lombok.extern.slf4j.Slf4j;
import net.exploringspringboot.journalApp.Entity.User;
import net.exploringspringboot.journalApp.Repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class UserService {
    @Autowired
    private UserEntryRepository userEntryRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    prte static final Logger logger = LoggerFactory.getLogger(UserService.class);


    public boolean saveNewUser(User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));

            userEntryRepository.save(user);
            return true;

        }catch (Exception e){
            log.info("Why so Sercious when you have 2 girls " , user.getUsername() ,e);
            return false;
        }
    }
    public void saveUser(User user){
        userEntryRepository.save(user);


    }
    public void saveAdmin(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER","ADMIN"));
        userEntryRepository.save(user);

    }


    public List<User> getAllEntries() {
        return userEntryRepository.findAll();
    }

    public Optional<User> findById(ObjectId id) {
        return userEntryRepository.findById(id);

    }
    public void deleteById(ObjectId id){
        userEntryRepository.deleteById(id);
    }
    public User findByUsername(String username){
        return userEntryRepository.findByUsername(username);
    }





}
