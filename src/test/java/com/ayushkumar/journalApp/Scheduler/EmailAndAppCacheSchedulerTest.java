package com.ayushkumar.journalApp.Scheduler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailAndAppCacheSchedulerTest {

    @Autowired
    private EmailAndAppCacheScheduler emailAndAppCacheScheduler;

    @Test
    public void getSAandSendMailTest(){
        emailAndAppCacheScheduler.getSAandSendMail();
        Assertions.assertTrue(true);
    }

}
