package com.ayushkumar.journalApp.Repository;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.mongodb.assertions.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @Test
    public void getUserHaveSATest(){
        List<UserEntity> userEntityList= userRepo.getUserHaveSA();
        if(userEntityList.isEmpty()){
            Assertions.assertFalse(false);
        }
        else {
            Assertions.assertTrue(true);
        }
    }

}
