package com.home_cycle.data.dto.response;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private String token;
    private String email;
    private int userId;
    private Integer householdId;

    public AuthResponseDTO(String token, String email, int userId, Integer householdId) {
        this.token = token;
        this.email = email;
        this.userId = userId;
        this.householdId = householdId;
    }
}
