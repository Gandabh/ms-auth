package com.group11.msauth.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.group11.msauth.jwt.JwtService;
import com.group11.msauth.users.dto.UserDto;
import com.group11.msauth.users.model.User;
import com.group11.msauth.users.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//@CrossOrigin(origins = "http://localhost:8080/") //@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // a new end-point that allows users to authenticate themselves and generate the jwt token
    //This endpoint will receive the userDto, authenticate her/him with existing users in the database, then if authenticated, it will create the jwt
    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody UserDto userDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getName(), userDto.getPassword()));
       // If the user is authenticated we generate the token, otherwise, we throw an exception
        log.info("authentication.isAuthenticated()  {} ", authentication);

        if (authentication.isAuthenticated()) {
        log.info("jwtService.generateToken(authRequest.getName())  {} ", jwtService.generateToken(userDto.getName()).toString());
            return jwtService.generateToken(userDto.getName());
        } else {
            throw new UsernameNotFoundException("The user cannot be authenticated");
        }
    }


    // an end point for signing up new users
    @PostMapping("/signup")
    public User signupUser(@RequestBody User user){
        return userService.addUser(user);
    }


    @GetMapping("/public")
    public String publicAPI() {
        return "This is an unprotected endpoint";
    }

    @GetMapping("/admin")
    public String adminAPI() {
        return "Protected endpoint - only admins are allowed";
    }

    @GetMapping("/user")
    public String userAPI() {
        return "Protected endpoint - only users are allowed";
    }
}
