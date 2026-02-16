package com.ayushkumar.journalApp.Controller;

import com.ayushkumar.journalApp.Entity.JournalEntity;
import com.ayushkumar.journalApp.Services.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    @Autowired
    private JournalServices journalServices;

    @PostMapping()
    public ResponseEntity<?> addJournalofUser(@RequestBody JournalEntity myJournal){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        JournalEntity createdJournal=journalServices.createJournal(userName,myJournal);
        if(createdJournal!=null){
            return new ResponseEntity<>(createdJournal, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(userName+" is not present", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> updateJournal(@PathVariable("id") ObjectId id,
                                           @RequestBody JournalEntity newJournal)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        JournalEntity updatedJournal=journalServices.updateJournal(userName,id,newJournal);
        if(updatedJournal!=null){
            return new ResponseEntity<>(updatedJournal,HttpStatus.OK);
        }
        return new ResponseEntity<>("id : "+id+" is not present",HttpStatus.NOT_FOUND);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable("id") ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        JournalEntity fetchedJournal=journalServices.findJournalById(userName,id);
        if(fetchedJournal!=null){
            return new ResponseEntity<>(fetchedJournal,HttpStatus.OK);
        }
        return new ResponseEntity<>(fetchedJournal,HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<?> getAllJournal(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        List<JournalEntity> journalEntities= journalServices.findAllJournal(userName);
        if(journalEntities!=null) {
            return new ResponseEntity<>(journalEntities, HttpStatus.OK);
        }
        return new ResponseEntity<>(userName+" is not present",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{id}")
    public ResponseEntity<?> removeJournalById(@PathVariable("id") ObjectId id){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        Boolean result=journalServices.deleteJournalById(userName,id);
        if(result){
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>("Journal is not present for mention id : "+id,HttpStatus.NOT_FOUND);
    }

}
