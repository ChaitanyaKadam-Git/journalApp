package net.exploringspringboot.journalApp.Repository;

import net.exploringspringboot.journalApp.Entity.ConfigJournalAppEntity;
import net.exploringspringboot.journalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalApp extends MongoRepository<ConfigJournalAppEntity, ObjectId> {



}
