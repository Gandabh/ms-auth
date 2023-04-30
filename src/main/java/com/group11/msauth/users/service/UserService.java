package com.group11.msauth.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group11.msauth.users.model.User;
import com.group11.msauth.users.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
        log.info("A new user  {} is added", user.toString());
        //return "A new user is added to system ";
        return user;
    } 
}
