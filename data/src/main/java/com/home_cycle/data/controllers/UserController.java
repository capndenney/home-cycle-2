package com.home_cycle.data.controllers;

import com.home_cycle.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // DO NOT add "get all users" for security

    // Get specific user information by ID

    // Add new user to database

    // Update user details

    // Delete User by ID
}
