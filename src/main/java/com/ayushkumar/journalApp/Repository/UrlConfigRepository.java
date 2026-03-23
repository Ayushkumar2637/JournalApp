package com.ayushkumar.journalApp.Repository;

import com.ayushkumar.journalApp.Entity.UrlConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlConfigRepository extends MongoRepository<UrlConfigEntity, ObjectId> {
    public UrlConfigEntity findByKey(String key);
}
