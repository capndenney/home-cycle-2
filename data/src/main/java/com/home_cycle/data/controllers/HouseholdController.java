package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.HouseholdDTO;
import com.home_cycle.data.models.Household;
import com.home_cycle.data.repositories.HouseholdRepository;
import com.home_cycle.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/household")
public class HouseholdController {

    @Autowired
    HouseholdRepository householdRepository;

    @Autowired
    private UserRepository userRepository;

    // DO NOT get all households for security
    // Get specific household by id
    @GetMapping("/{id}")
    public ResponseEntity<?> getHouseholdById(@PathVariable int id) {
        return householdRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new household
    @PostMapping("/new")
    public ResponseEntity<?> createHousehold(@Validated @RequestBody HouseholdDTO householdDTO) {
        Household household = new Household();
        household.setNotes(householdDTO.getNotes());
        household.setTasks(householdDTO.getTasks());
        household.setUsers(householdDTO.getUsers());
        household.setCreatedAt(Instant.now());
        Household savedHousehold = householdRepository.save(household);
        return ResponseEntity.ok(savedHousehold);
    }

    // TODO: Create signup link user can share for others to join household

    // Update existing household
    @PutMapping("/{id}")
    public ResponseEntity<?> updateHousehold(@PathVariable int id, @Validated @RequestBody HouseholdDTO householdDTO) {
        return householdRepository.findById(id)
                .map(household -> {
                    household.setNotes(householdDTO.getNotes());
                    household.setTasks(householdDTO.getTasks());
                    household.setUsers(householdDTO.getUsers());
                    Household updatedHousehold = householdRepository.save(household);
                    return ResponseEntity.ok(updatedHousehold);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete household by id
    // TODO: Add service that handles succession logic
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHousehold(@PathVariable int id) {
        return householdRepository.findById(id)
                .map(household -> {
                    householdRepository.delete(household);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
