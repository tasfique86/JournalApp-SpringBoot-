package net.engineeringdigest.journalApp2.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.engineeringdigest.journalApp2.enums.Sentiment;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;


@Document(collection= "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntry {
    @Id
    private ObjectId id;
    private String title;
    private String content;

    private LocalDateTime publishedDate;

    private Sentiment sentiment;


//    public LocalDateTime getPublishedDate() {
//        return publishedDate;
//    }
//
//    public void setPublishedDate(LocalDateTime publishedDate) {
//        this.publishedDate = publishedDate;
//    }
//
//    public ObjectId getId() {
//        return id;
//    }
//
//    public void setId(ObjectId id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
