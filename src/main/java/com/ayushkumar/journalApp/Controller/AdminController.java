package com.ayushkumar.journalApp.Controller;

import com.ayushkumar.journalApp.AppCache.AppCache;
import com.ayushkumar.journalApp.Entity.UrlConfigEntity;
import com.ayushkumar.journalApp.Services.UrlConfigServices;
import com.ayushkumar.journalApp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ayushkumar.journalApp.Entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userServices;
    @Autowired
    private AppCache appCache;
    @Autowired
    private UrlConfigServices urlConfigServices;

    @GetMapping
    public ResponseEntity<?> getAllUser(){
        List<UserEntity> userEntityList=userServices.findAllUser();
        List<List<Object>> userInfo=new ArrayList<>();
        userInfo=userEntityList
                .stream()
                .map(s->List.of(s.getUserName(),s.getRoles()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userInfo,HttpStatus.OK);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateRole(@PathVariable("userName") String userName){
        UserEntity updatedUser=userServices.updateUserRole(userName);
        if(updatedUser!=null){
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>("The user you are trying to upadate is not prsent in database",HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createUrlConfig(@RequestBody UrlConfigEntity urlConfigEntity){
        UrlConfigEntity savedUrlConfigEntity=urlConfigServices.createUrlConfig(urlConfigEntity);
        return new ResponseEntity<>(savedUrlConfigEntity,HttpStatus.CREATED);
    }

    @GetMapping("/get-all-url")
    public ResponseEntity<?> getAllUrlConfig(){
        List<UrlConfigEntity> urlConfigEntityList=urlConfigServices.findAllValues();
        appCache.init();
        return new ResponseEntity<>(urlConfigEntityList,HttpStatus.OK);
    }

}
