package net.engineeringdigest.journalApp2.repository;


import net.engineeringdigest.journalApp2.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalRepository extends MongoRepository<JournalEntry, ObjectId> {

}
