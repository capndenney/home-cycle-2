package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.UserDTO;
import com.home_cycle.data.models.User;
import com.home_cycle.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // DO NOT add "get all users" for security
    // Get specific user information by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new user to database
    @PostMapping("/new")
    public ResponseEntity<?> createUser(@Validated @RequestBody UserDTO userDTO) {
        User newUser = new User();
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());
        newUser.setName(userDTO.getName());
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    // Update user details

    // "Delete" User by ID
    // TODO: Mark user as deleted, and append "deleted" to email to allow for reuse of email address if user deletes account and creates new one.
}
