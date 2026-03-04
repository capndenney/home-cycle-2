package com.home_cycle.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenValidationRequestDTO {
    private String token;
    private String email;

    public TokenValidationRequestDTO() {
    }

    public TokenValidationRequestDTO(String token, String email) {
        this.token = token;
        this.email = email;
    }
}
