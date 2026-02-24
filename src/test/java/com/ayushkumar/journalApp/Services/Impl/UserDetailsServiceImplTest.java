package com.ayushkumar.journalApp.Services.Impl;

import com.ayushkumar.journalApp.Entity.UserEntity;
import com.ayushkumar.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    private UserEntity user;

    @BeforeEach
    public void userEntityMock(){
        user =new UserEntity();
        user.setUserName("ram");
        user.setPassword("454dfd");
        user.setRoles(new ArrayList<>(Arrays.asList("USER")));

        // If we use below method than we did not need @SpringBootTest because it inject the mock and userRepository is not null
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loadUserByUsernameTest(){
        when(userRepository.findByUserName("ram")).thenReturn(Optional.of(user));
        UserDetails fetchedUser=userDetailsServiceImpl.loadUserByUsername("ram");
        Assertions.assertNotNull(fetchedUser);
    }

}
