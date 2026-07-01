package com.insurance.insurance_parent.dto;

import com.insurance.insurance_parent.entity.Role;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Role role;
}