package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Services.EmailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceImplTest {

    @Autowired
    private EmailService emailService;

    @Test
    public void sendMailTest(){
        boolean value=emailService.sendMail("ayushkumar2637@gmail.com","Test","Third time testing mail by spring boot journal app");
        Assertions.assertTrue(value);
    }

}
