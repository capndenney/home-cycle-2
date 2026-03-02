package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.RegistrationDTO;
import com.home_cycle.data.dto.response.HouseholdResponseDTO;
import com.home_cycle.data.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registration;

    @PostMapping
    public ResponseEntity<HouseholdResponseDTO> registerHousehold(@RequestBody RegistrationDTO dto) {
        HouseholdResponseDTO response = registration.registerHousehold(dto);
        return ResponseEntity.ok(response);
    }
}
