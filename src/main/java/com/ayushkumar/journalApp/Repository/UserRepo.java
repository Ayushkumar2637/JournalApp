package com.ayushkumar.journalApp.Repository;

import com.ayushkumar.journalApp.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntity> getUserHaveSA(){
        Query query=new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        query.addCriteria(Criteria.where("semanticAnalysis").is(true));
        List<UserEntity> userEntityList=mongoTemplate.find(query,UserEntity.class);
        return userEntityList;
    }

    /* We can also create Criteria object and also do place and , or operators between two conditions */

}
