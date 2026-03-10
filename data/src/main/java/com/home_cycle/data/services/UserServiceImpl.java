package com.home_cycle.data.services;

import com.home_cycle.data.dto.request.PasswordDTO;
import com.home_cycle.data.dto.request.UserDTO;
import com.home_cycle.data.dto.request.UserRequestDTO;
import com.home_cycle.data.models.User;
import com.home_cycle.data.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.OneToOne;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public boolean updatePassword(String email, PasswordDTO passwordDTO) {
        User user = userRepository.findByEmail(email).orElse(null);

        if (user != null && encoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            user.setPassword(encoder.encode(passwordDTO.getNewPassword()));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public User patchUser(int id, UserRequestDTO dto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User Not Found"));
        if (dto.getName() != null) {
            existingUser.setName(dto.getName());
        }
        return userRepository.save(existingUser);
    }
}
