package com.home_cycle.data.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private Integer id;
    private String email;
    private String name;
    private boolean isPrimary;
    private Instant createdAt;

    public UserResponseDTO(Integer id, String email, String name, boolean isPrimary) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.isPrimary = isPrimary;
    }
}
