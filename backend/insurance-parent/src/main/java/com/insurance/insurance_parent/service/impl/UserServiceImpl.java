package com.insurance.insurance_parent.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.insurance.insurance_parent.dto.LoginRequestDTO;
import com.insurance.insurance_parent.dto.LoginResponseDTO;
import com.insurance.insurance_parent.dto.UserRequestDTO;
import com.insurance.insurance_parent.dto.UserResponseDTO;
import com.insurance.insurance_parent.entity.User;
import com.insurance.insurance_parent.repository.UserRepository;
import com.insurance.insurance_parent.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        
    }

    @Override
    public UserResponseDTO registerUser(UserRequestDTO request) {
         
          if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
        }
        // DTO -> Entity
        User user = modelMapper.map(request, User.class);

        
        // Save into DB
        User savedUser = userRepository.save(user);

        // Entity -> DTO
        return modelMapper.map(savedUser, UserResponseDTO.class);
    }

    @Override
public LoginResponseDTO login(LoginRequestDTO request) {

    User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

    if (!user.getPassword().equals(request.getPassword())) {
        throw new RuntimeException("Invalid Password");
    }

    return new LoginResponseDTO(
            user.getId(),
            user.getFirstName(),
            user.getEmail(),
            user.getRole().name(),
            "Login Successful"
    );
}
}