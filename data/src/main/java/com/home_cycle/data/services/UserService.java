package com.home_cycle.data.services;

import com.home_cycle.data.dto.request.UserDTO;
import com.home_cycle.data.models.User;

public interface UserService {
    UserDTO createNewUser(UserDTO userDTO);

    User findByEmail(String email);
}
