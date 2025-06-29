package net.engineeringdigest.journalApp.repository;


import net.engineeringdigest.journalApp.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface JournalEntryRepo extends MongoRepository<JournalEntry, ObjectId> {

    //public Optional<JournalEntry> findById(String id);
}
