package net.engineeringdigest.journalApp2.controller;

import net.engineeringdigest.journalApp2.entity.JournalEntry;
import net.engineeringdigest.journalApp2.entity.User;
import net.engineeringdigest.journalApp2.service.JournalEntryService;
import net.engineeringdigest.journalApp2.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {


  @Autowired
  private JournalEntryService journalEntryService;

  @Autowired
  private UserService userService;

    @GetMapping
    public ResponseEntity<?> getAllJournalEntry() {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();

        User user=userService.findByUsername(userName);


        List<JournalEntry> all =user.getJournalEntries();
        if(all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(all,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<JournalEntry>  addJournalEntry(@RequestBody JournalEntry journalEntry) {
        try {
         //   journalEntry.setPublishedDate(LocalDateTime.now());
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            String userName = authentication.getName();

            journalEntryService.saveEntry(journalEntry,userName);
            return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getAllJournalEntry(@PathVariable ObjectId myId) {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUsername(userName);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = Optional.ofNullable(journalEntryService.findById(myId));
            if(journalEntry.isPresent())
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @GetMapping
//    public List<JournalEntry> getJournalEntry() {
//        return journalEntryService.findAll();
//    }

    @DeleteMapping("id/{myId}")
    public  ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId  myId) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        boolean remove=journalEntryService.delete(myId,userName);
        if(remove)
             return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("id/{myId}")
    public  ResponseEntity<?> updateJournalEntryById(@RequestBody JournalEntry newjournalEntry,@PathVariable ObjectId  myId) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=userService.findByUsername(userName);
        List<JournalEntry> collect=user.getJournalEntries().stream().filter(x->x.getId().equals(myId)).collect(Collectors.toList());

        if(!collect.isEmpty()) {
            Optional<JournalEntry> journalEntry = Optional.ofNullable(journalEntryService.findById(myId));
            if(journalEntry.isPresent()){
                JournalEntry oldjournalEntry=journalEntry.get();
                oldjournalEntry.setContent(newjournalEntry.getContent()!=null && !newjournalEntry.getContent().equals("") ? newjournalEntry.getContent() : oldjournalEntry.getContent());
                oldjournalEntry.setTitle(newjournalEntry.getTitle()!=null && !newjournalEntry.getTitle().equals("")?newjournalEntry.getTitle():oldjournalEntry.getTitle());
                journalEntryService.saveEntry(oldjournalEntry);
                return new ResponseEntity<>(oldjournalEntry, HttpStatus.OK);
            }

        }

        JournalEntry oldjournalEntry = journalEntryService.findById(myId);
        if(oldjournalEntry != null) {


        }


         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
