package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.RegistrationDTO;
import com.home_cycle.data.dto.response.HouseholdResponseDTO;
import com.home_cycle.data.services.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registration;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<HouseholdResponseDTO> registerHousehold(@RequestBody @Valid RegistrationDTO dto) {
        HouseholdResponseDTO response = registration.registerHousehold(dto);
        return ResponseEntity.ok(response);
    }
}
