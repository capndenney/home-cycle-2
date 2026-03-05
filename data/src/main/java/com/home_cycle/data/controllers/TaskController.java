package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.TaskDTO;
import com.home_cycle.data.models.Household;
import com.home_cycle.data.models.Task;
import com.home_cycle.data.models.User;
import com.home_cycle.data.repositories.HouseholdRepository;
import com.home_cycle.data.repositories.TaskRepository;
import com.home_cycle.data.repositories.UserRepository;
import com.home_cycle.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private UserService userService;

    // Get all tasks
    @GetMapping("")
    public ResponseEntity<?> getAllTasks(Principal principal) {
        User user = userService.findByEmail(principal.getName());
        List<Task> filteredTasks = taskRepository.findByHouseholdIdOrCreatedBy_Id(
                user.getHousehold() != null ? user.getHousehold().getId() : null,
                user.getId()
        );
        return ResponseEntity.ok(filteredTasks);
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
    @PostMapping("/newtask")
    public ResponseEntity<?> createTask(@Validated @RequestBody TaskDTO taskDTO) {
        User user = userRepository.findById(taskDTO.getCreatedBy()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        Task task = new Task();
        Household household = householdRepository.findById(taskDTO.getHouseholdId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Household not found"));
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setHousehold(household);
        task.setDueDate(taskDTO.getDueDate());
        task.setCompleted(taskDTO.isCompleted());
        task.setRecurrence(taskDTO.getRecurrence()); // TODO: use plusDays for date math
        task.setCreatedBy(user);
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
    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeTask(@PathVariable int id, @RequestBody int userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return taskRepository.findById(id)
                .map(task -> {
                    task.setCompleted(true);
                    task.setCompletedAt(Instant.now());
                    task.setCompletedBy(user);
                    if (task.getRecurrence() > 0) {
                        Task nextTask = new Task();
                        nextTask.setTitle(task.getTitle());
                        nextTask.setDescription(task.getDescription());
                        nextTask.setHousehold(task.getHousehold());
                        nextTask.setCreatedBy(task.getCreatedBy());
                        nextTask.setDueDate(task.getDueDate().plusDays(task.getRecurrence()));
                        nextTask.setRecurrence(task.getRecurrence());
                        taskRepository.save(nextTask);
                    }
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
