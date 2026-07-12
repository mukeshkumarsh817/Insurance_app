package com.insurance.insurance_parent.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurance.insurance_parent.dto.UserRequestDTO;
import com.insurance.insurance_parent.dto.UserResponseDTO;
import com.insurance.insurance_parent.service.UserService;
import com.insurance.insurance_parent.dto.LoginRequestDTO;
import com.insurance.insurance_parent.dto.LoginResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
   /// mukesh kumar sharma
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO request) {

        UserResponseDTO response = userService.registerUser(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {

    LoginResponseDTO response = userService.login(request);

    return ResponseEntity.ok(response);
}

}