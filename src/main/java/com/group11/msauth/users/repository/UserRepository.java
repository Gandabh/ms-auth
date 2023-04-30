package com.group11.msauth.users.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


import  com.group11.msauth.users.model.User;

public interface UserRepository extends JpaRepository<User, UUID>{
    Optional <User> findByName(String username);
}
