package com.group11.msauth.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.group11.msauth.users.model.User;
import com.group11.msauth.users.repository.UserRepository;


@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
       Optional<User> user = userRepository.findByName(username);
       user
       .orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
               return user.map(MyUserDetails::new).get();
   }
}
