package com.home_cycle.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDTO {
    @NotBlank
    private String oldPassword;
    @NotBlank
    @Size(min=8)
    private String newPassword;
}
