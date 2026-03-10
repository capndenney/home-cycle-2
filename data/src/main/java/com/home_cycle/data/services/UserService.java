package com.home_cycle.data.services;

import com.home_cycle.data.dto.request.PasswordDTO;
import com.home_cycle.data.dto.request.UserDTO;
import com.home_cycle.data.dto.request.UserRequestDTO;
import com.home_cycle.data.models.User;

public interface UserService {
    UserDTO createNewUser(UserDTO userDTO);

    User findByEmail(String email);

    boolean updatePassword(String email, PasswordDTO passwordDTO);

    User patchUser(int id, UserRequestDTO userDTO);
}
