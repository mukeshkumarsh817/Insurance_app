

package com.insurance.insurance_parent.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;

    private String firstName;

    private String email;

    private String role;

    private String message;
}