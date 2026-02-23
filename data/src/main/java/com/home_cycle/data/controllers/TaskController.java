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
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

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
    @PostMapping(value = "/newtask")
    public ResponseEntity<?> createTask(@Validated @RequestBody TaskDTO taskDTO) {
        User user = userRepository.findById(taskDTO.getCreatedBy()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Task task = new Task();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setHouseholdId(taskDTO.getHouseholdId());
        task.setDueDate(taskDTO.getDueDate());
        task.setCompleted(taskDTO.isCompleted());
        task.setCompletedAt(taskDTO.getCompletedAt());
        task.setRecurrence(taskDTO.getRecurrence()); // TODO: use plusDays for date math
        task.setCreatedBy(user.getId());
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTask);
    }

    // Update existing task - not completion status
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable int id, @Validated @RequestBody TaskDTO taskDTO) {
        return taskRepository.findById(id)
                .map(existingTask -> {
                    existingTask.setTitle(taskDTO.getTitle());
                    existingTask.setDescription(taskDTO.getDescription());
                    existingTask.setDueDate(taskDTO.getDueDate());
                    existingTask.setRecurrence(taskDTO.getRecurrence());
                    Task updatedTask = taskRepository.save(existingTask);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update task completion status
    @PatchMapping("/{id}/complete")
    public ResponseEntity<?> completeTask(@PathVariable int id, @RequestParam int userId) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(true);
                    task.setCompletedAt(System.currentTimeMillis());
                    task.setCompletedBy(userId);
                    Task updatedTask = taskRepository.save(task);
                    return ResponseEntity.ok(updatedTask);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete task by id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable int id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
