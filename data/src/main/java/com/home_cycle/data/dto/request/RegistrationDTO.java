package com.home_cycle.data.dto.request;

import lombok.Data;

@Data
public class RegistrationDTO {
    // Primary User Info
    private String email;
    private String password;
    private String name;
    // Household Info
    private String householdNotes;
}
