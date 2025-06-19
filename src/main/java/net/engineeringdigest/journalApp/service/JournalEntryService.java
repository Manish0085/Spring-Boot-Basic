package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepo;
import net.engineeringdigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {


    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;


    @Transactional
    public JournalEntry createEntry(String username, JournalEntry journalEntry) {
        User user = userService.getUserByUserName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        journalEntry.setDate(LocalDate.now());
        journalEntryRepo.save(journalEntry);

//        user.setUserName(null); // force error
        user.getJournalEntries().add(journalEntry);
        userService.saveUser(user);

        return journalEntry;
    }

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    @Transactional
    public void deleteEntry(String username, ObjectId id) {
        JournalEntry entry = journalEntryRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entry not found"));

        User user = userRepo.findByUserName(username);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        boolean removed = user.getJournalEntries().removeIf(e -> e.getId().equals(id));
        if (removed) {
            userRepo.save(user);

            journalEntryRepo.deleteById(id);
        }
    }

    public List<JournalEntry> fetchAllEntry(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> fetchEntry(ObjectId id){
        return journalEntryRepo.findById(id);

    }



}
