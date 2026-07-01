package com.insurance.insurance_parent.service;

import com.insurance.insurance_parent.dto.LoginRequestDTO;
import com.insurance.insurance_parent.dto.LoginResponseDTO;
import com.insurance.insurance_parent.dto.UserRequestDTO;
import com.insurance.insurance_parent.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO registerUser(UserRequestDTO request);
    LoginResponseDTO login(LoginRequestDTO request);

}