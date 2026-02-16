package com.ayushkumar.journalApp.Controller;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserServices userServices;

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserEntity user){
        UserEntity createdUser=userServices.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> status(){
        return new ResponseEntity<>("Application is up", HttpStatus.OK);
    }

}
