package com.ayushkumar.journalApp.Controller;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    // updatedUser will not become null after security machinism apply
    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntity newUser){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity updatedUser=userServices.updateUser(username,newUser);
        if(updatedUser!=null){
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(updatedUser,HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<?> getUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity fetchedUser=userServices.findUserByUserName(userName);
        if(fetchedUser!=null){
            return new ResponseEntity<>(fetchedUser,HttpStatus.OK);
        }
        return new ResponseEntity<>(fetchedUser,HttpStatus.NOT_FOUND);
    }


    @DeleteMapping()
    public ResponseEntity<?> removeUserById(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        UserEntity fetchedUser=userServices.findUserByUserName(userName);
        boolean result=userServices.deleteUserById(fetchedUser.getId());
        if(result){
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

}
