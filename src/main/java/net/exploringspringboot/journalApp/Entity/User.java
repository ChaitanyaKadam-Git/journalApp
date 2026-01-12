package net.exploringspringboot.journalApp.Entity;

import lombok.*;
import net.exploringspringboot.journalApp.Repository.UserEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.lang.model.type.UnionType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//this is called POJO class plan old java object
@Document (collection = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry>journalEntries = new ArrayList<>();

    private String email;
    private boolean sentimentalAnalysis;

    private List<String>roles;





}
