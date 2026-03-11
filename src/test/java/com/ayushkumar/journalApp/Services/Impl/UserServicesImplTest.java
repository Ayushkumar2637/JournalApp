package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Services.UserServices;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("dev")
@SpringBootTest
public class UserServicesImplTest {

    @Autowired
    private UserServices userServices;

    @Disabled
    @Test
    public void testingMethod1(){
        Assertions.assertEquals(4,3+1);
    }

    @CsvSource({
            "5,2,3",
            "10,9,1",
            "9,2,2"
    })
    @ParameterizedTest
    public void testingMethod2(int expected,int a,int b){
        Assertions.assertEquals(expected,a+b);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "pawan",
            "ayush",
            "vivek",
            "chandan"
    })
    public void findUserByUserNameTest(String userName){
        UserEntity fetchedUser=userServices.findUserByUserName(userName);
        Assertions.assertNotNull(fetchedUser);
    }

    @ParameterizedTest
    @ArgumentsSource(ArgumentProvider.class)
    public void findUserByUserNameTest2(String userName){
        UserEntity fetchedUser=userServices.findUserByUserName(userName);
        Assertions.assertNotNull(fetchedUser);
    }

    @Test
    public void findAllUserTest(){
        List<UserEntity> userEntityList=userServices.findAllUser();
        Assertions.assertNotNull(userEntityList);
    }

    @Test
    public void deleteUserByIdTest(){
        boolean res=userServices.deleteUserById(new ObjectId("69877641b1644e8914a1c3ce"));
        Assertions.assertFalse(res);
    }

//    @BeforeAll
//    @AfterAll
//    @AfterEach
    @BeforeEach
    public void moreAnnotation(){
        System.out.println("Annotation convering");
    }

}
