package com.ayushkumar.journalApp.Services;

import com.ayushkumar.journalApp.Entity.UserEntity;
import org.bson.types.ObjectId;

import java.util.List;

public interface UserServices {
    public UserEntity createUser(UserEntity user);
    public UserEntity updateUser(ObjectId objectId,UserEntity newUser);
    public UserEntity updateUser(String userName,UserEntity newUser);
    public UserEntity updateUserRole(String userName);
    public UserEntity findUserById(ObjectId objectId);
    public List<UserEntity> findAllUser();
    public boolean deleteUserById(ObjectId objectId);
    public UserEntity findUserByUserName(String userName);
}
