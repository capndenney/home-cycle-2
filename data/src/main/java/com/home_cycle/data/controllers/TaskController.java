package com.home_cycle.data.controllers;

import com.home_cycle.data.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    // Get all tasks

    // Get specific task by id

    // Add new task

    // Update existing task

    // Delete task by id
}
