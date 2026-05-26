package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Model.SementicData;
import com.ayushkumar.journalApp.Services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerServiceImpl {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "userSementic",groupId = "usersementic-group")
    public void sementicConsumer(SementicData sementicData){
        boolean res =emailService.sendMail(sementicData.getMail(),"Sementic Mail",sementicData.getSementic());
        log.info("Mail is sent via Kafka : "+res);
    }

}
