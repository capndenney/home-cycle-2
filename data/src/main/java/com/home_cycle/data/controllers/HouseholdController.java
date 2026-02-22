package com.home_cycle.data.controllers;

import com.home_cycle.data.repositories.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HouseholdController {

    @Autowired
    HouseholdRepository householdRepository;

        // DO NOT get all households for security

        // Get specific household by id

        // Add new household

        // Update existing household

        // Delete household by id
}
