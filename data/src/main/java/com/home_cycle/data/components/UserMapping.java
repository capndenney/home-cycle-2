package com.home_cycle.data.components;

import com.home_cycle.data.dto.response.UserResponseDTO;
import com.home_cycle.data.models.User;

public class UserMapping {
    public UserResponseDTO toDTO(User user) {
        return new UserResponseDTO(
            user.getId(),
            user.getEmail(),
            user.getName(),
            user.isPrimary()
        );
    }
}
