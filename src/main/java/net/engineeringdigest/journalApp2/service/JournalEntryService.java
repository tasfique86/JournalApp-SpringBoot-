package net.engineeringdigest.journalApp2.service;

import net.engineeringdigest.journalApp2.entity.JournalEntry;
import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.repository.JournalRepository;
import org.bson.types.ObjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class JournalEntryService {

 @Autowired
 private JournalRepository journalRepository;

 @Autowired
 private UserService userService;


//
 @Transactional
 public void saveEntry(JournalEntry journalEntry, String userName) {
     try {
         User user=userService.findByUsername(userName);
         journalEntry.setPublishedDate(LocalDateTime.now());
         JournalEntry save=journalRepository.save(journalEntry);
         user.getJournalEntries().add(save);
         userService.saveUser(user);

     }catch (Exception e){
           System.out.println(e);
           throw new RuntimeException("Error saving journal entry");
     }

  }


    public void saveEntry(JournalEntry journalEntry) {
       journalRepository.save(journalEntry);
    }

  public List<JournalEntry> findAll() {
   return journalRepository.findAll();
  }

  public JournalEntry findById(ObjectId id) {
   return journalRepository.findById(id).orElse(null);

  }
@Transactional
  public boolean delete(ObjectId id, String userName) {
    boolean remove=false;
    try {
        User user=userService.findByUsername(userName);
        remove=user.getJournalEntries().removeIf(x-> x.getId().equals(id));

        if(remove){
            userService.saveUser(user);
            journalRepository.deleteById(id);
        }
    }catch (Exception e){
        System.out.println(e);;
        throw new RuntimeException("Error deleting journal entry");
    }
    return remove;
  }

 public void updateJournalById(JournalEntry journalEntry) {
  journalRepository.save(journalEntry);
 }

// public List<JournalEntry> findByUserName(String userName) {
//
// }
}
