package com.ayushkumar.journalApp.Repository;

import com.ayushkumar.journalApp.Entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
    public Optional<UserEntity> findByUserName(String userName);
}

