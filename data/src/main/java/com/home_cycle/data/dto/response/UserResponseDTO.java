package com.home_cycle.data.dto.response;

import lombok.Data;

import java.security.Timestamp;

@Data
public class UserResponseDTO {
    // TODO: Do we still need ID in this?
    private Integer id;
    private String email;
    private String name;
    private boolean isPrimary;
    private Timestamp createdAt;

    public UserResponseDTO(Integer id, String email, String name, boolean isPrimary) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.isPrimary = isPrimary;
    }
}
