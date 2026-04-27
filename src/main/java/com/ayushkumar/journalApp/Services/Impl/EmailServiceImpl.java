package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Services.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender){
        this.javaMailSender=javaMailSender;
    }

    @Override
    public boolean sendMail(String to, String sub, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(sub);
            simpleMailMessage.setText(body);
            javaMailSender.send(simpleMailMessage);
            return true;
        }
        catch (Exception e){
            log.error("There is an exception while sending the mail ",e);
        }
        return false;
    }
}
