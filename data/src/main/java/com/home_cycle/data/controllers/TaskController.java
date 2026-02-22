package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.TaskDTO;
import com.home_cycle.data.models.Task;
import com.home_cycle.data.models.User;
import com.home_cycle.data.repositories.TaskRepository;
import com.home_cycle.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    @GetMapping("")
    public ResponseEntity<?> getAllTasks() {
        List<Task> allTasks = taskRepository.findAll();
        return ResponseEntity.ok(allTasks);
    }

    // Get specific task by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable int id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new task
    // TODO: Determine connection point to front end and link found user ID to task creation
//    @PostMapping(value = "/newtask")
//    public ResponseEntity<?> createTask(@Validated @RequestBody TaskDTO taskDTO) {
//        User user = UserRepository.findById(taskDTO.getCreatedBy()).orElse(null);
//        Task task = new Task()
//    }

    // Update existing task


    // Delete task by id


}
