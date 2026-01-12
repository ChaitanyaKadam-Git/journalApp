package net.exploringspringboot.journalApp.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.exploringspringboot.journalApp.Enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//this is called POJO class plan old java object
@Document (collection = "journaldb")
@Data
@AllArgsConstructor

@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiment sentiment;


}
