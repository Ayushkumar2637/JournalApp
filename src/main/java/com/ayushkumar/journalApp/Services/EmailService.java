package com.ayushkumar.journalApp.Services;

public interface EmailService {
    public boolean sendMail(String to,String sub,String body);
}
