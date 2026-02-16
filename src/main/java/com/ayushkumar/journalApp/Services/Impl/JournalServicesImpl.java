package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Entity.JournalEntity;
import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Repository.JournalRepository;
import com.ayushkumar.journalApp.Repository.UserRepository;
import com.ayushkumar.journalApp.Services.JournalServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServicesImpl implements JournalServices {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public JournalEntity createJournal(String userName,JournalEntity myJournal) {
        UserEntity fetchedUser=userRepository.findByUserName(userName).orElse(null);
        if(fetchedUser!=null) {
            myJournal.setDateTime(LocalDateTime.now());
            JournalEntity createdJournal = journalRepository.save(myJournal);
            fetchedUser.getJournalEntities().add(createdJournal);
            userRepository.save(fetchedUser);
            return createdJournal;
        }
        return null;
    }

    @Override
    public JournalEntity updateJournal(String userName,ObjectId id, JournalEntity newJournal) {
        UserEntity fetchedUser=userRepository.findByUserName(userName).orElse(null);
        if(fetchedUser!=null) {
            JournalEntity oldjournal = fetchedUser.getJournalEntities()
                    .stream()
                    .filter(x -> x.getId().equals(id))
                    .findAny()
                    .orElse(null);
            if (oldjournal != null) {
                oldjournal.setTitle((newJournal.getTitle() != null && !newJournal.getTitle().equals("")) ? newJournal.getTitle() : oldjournal.getTitle());
                oldjournal.setContent((newJournal.getContent() != null && !newJournal.getContent().equals("")) ? newJournal.getContent() : oldjournal.getContent());
                journalRepository.save(oldjournal);
            }
            return oldjournal;
        }
        else{
            return null;
        }
    }

    @Override
    public JournalEntity findJournalById(String userName,ObjectId objectId) {
        Optional<UserEntity> dbUser=userRepository.findByUserName(userName);
        JournalEntity fetchedJournal=null;
        if(dbUser.isPresent()){
            fetchedJournal=dbUser.get().getJournalEntities()
                    .stream()
                    .filter(x->x.getId().equals(objectId))
                    .findAny()
                    .orElse(null);
        }
        return fetchedJournal;
    }

    @Override
    public List<JournalEntity> findAllJournal(String userName) {
        UserEntity fetchedUser=userRepository.findByUserName(userName).orElse(null);
        if(fetchedUser!=null){
            List<JournalEntity> journalEntityList=fetchedUser.getJournalEntities();
            return journalEntityList;
        }
        return null;
    }

    @Transactional
    @Override
    public boolean deleteJournalById(String userName,ObjectId id) {
        UserEntity fetchedUser=userRepository.findByUserName(userName).orElse(null);
        if(fetchedUser!=null){
            JournalEntity fetchedJournal = fetchedUser.getJournalEntities()
                    .stream()
                    .filter(x -> x.getId().equals(id))
                    .findAny()
                    .orElse(null);
            if(fetchedJournal!=null){
                fetchedUser.getJournalEntities().remove(fetchedJournal);
                userRepository.save(fetchedUser);
                journalRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }
}
