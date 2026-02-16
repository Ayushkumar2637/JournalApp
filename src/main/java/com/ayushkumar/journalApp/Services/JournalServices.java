package com.ayushkumar.journalApp.Services;

import com.ayushkumar.journalApp.Entity.JournalEntity;
import org.bson.types.ObjectId;

import java.util.List;

public interface JournalServices {
    public JournalEntity createJournal(String userName,JournalEntity myJournal);
    public JournalEntity updateJournal(String userName,ObjectId objectId,JournalEntity newJournal);
    public JournalEntity findJournalById(String userName,ObjectId objectId);
    public List<JournalEntity> findAllJournal(String userName);
    public boolean deleteJournalById(String userName,ObjectId objectId);
}
