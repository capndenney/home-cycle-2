package com.home_cycle.data.services;

import com.home_cycle.data.dto.request.UserDTO;
import com.home_cycle.data.models.User;
import com.home_cycle.data.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder encoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserDTO createNewUser (UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EntityExistsException("User with email " + userDTO.getEmail() + " already exists");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        User user = mapToProfileEntity(userDTO);
        User savedUser = userRepository.save(user);
        return mapToProfileDTO(savedUser);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("User with email " + email + " not found"));
    }

    private UserDTO mapToProfileDTO(User profile) {
        return modelMapper.map(profile, UserDTO.class);
    }

    private User mapToProfileEntity(UserDTO profileDTO) {
        return modelMapper.map(profileDTO, User.class);
    }
}
