package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity dbUser=userRepository.findByUserName(username).orElse(null);
        if(dbUser!=null){
            UserDetails dbUserDetails=User
                    .builder()
                    .username(dbUser.getUserName())
                    .password(dbUser.getPassword())
                    .roles(dbUser.getRoles().toArray(new String[0]))
                    .build();
            return dbUserDetails;
        }
        throw new UsernameNotFoundException("User name is not found for "+username);
    }
}
