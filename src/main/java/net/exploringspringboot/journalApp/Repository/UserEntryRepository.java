package net.exploringspringboot.journalApp.Repository;

import net.exploringspringboot.journalApp.Entity.JournalEntry;
import net.exploringspringboot.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    void deleteByUsername(String username);


}
