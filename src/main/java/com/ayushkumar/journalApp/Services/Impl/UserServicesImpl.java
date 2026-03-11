package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Repository.UserRepository;
import com.ayushkumar.journalApp.Services.UserServices;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Override
    public UserEntity createUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add("USERS");
        UserEntity createdUser=userRepository.save(user);
        return createdUser;
    }

    @Override
    public UserEntity updateUser(ObjectId objectId, UserEntity newUser) {
        UserEntity oldUser=userRepository.findById(objectId).orElse(null);
        if(oldUser!=null){
            oldUser.setUserName((newUser.getUserName()!=null && !newUser.getUserName().equals(""))?newUser.getUserName():oldUser.getUserName());
            oldUser.setPassword((newUser.getPassword()!=null && !newUser.getPassword().equals(""))?newUser.getPassword():oldUser.getUserName());
            userRepository.save(oldUser);
        }
        return oldUser;
    }

    @Override
    public UserEntity updateUser(String userName, UserEntity newUser) {
        UserEntity oldUser=userRepository.findByUserName(userName).orElse(null);
        if(oldUser!=null){
            if(newUser.getUserName()!=null && !newUser.getUserName().equals("")){
                oldUser.setUserName(newUser.getUserName());
            }
            if(newUser.getPassword()!=null && !newUser.getPassword().equals("")){
                oldUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            }

            userRepository.save(oldUser);
        }
        return oldUser;
    }

    @Override
    public UserEntity updateUserRole(String userName) {
        UserEntity dbUser=userRepository.findByUserName(userName).orElse(null);
        if(dbUser!=null){
            dbUser.getRoles().add("ADMIN");
            userRepository.save(dbUser);
        }
        log.warn("User {} get all admin rights",userName);
        return dbUser;
    }

    @Override
    public UserEntity findUserById(ObjectId objectId) {
        UserEntity fetchedUser=userRepository.findById(objectId).orElse(null);
        return fetchedUser;
    }

    @Override
    public List<UserEntity> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(ObjectId objectId) {
        UserEntity fetchedUser=userRepository.findById(objectId).orElse(null);
        if(fetchedUser!=null){
            userRepository.deleteById(objectId);
            log.warn("User {} is deleted from database",fetchedUser);
            return true;
        }
        return false;
    }

    @Override
    public UserEntity findUserByUserName(String userName) {
        UserEntity fetchedUser=userRepository.findByUserName(userName).orElse(null);
        return fetchedUser;
    }
}
