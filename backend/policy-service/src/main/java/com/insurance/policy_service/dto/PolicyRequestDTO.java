
package com.insurance.policy_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PolicyRequestDTO {

    @NotBlank(message = "Policy Number is required")
    private String policyNumber;

    @NotBlank(message = "Policy Name is required")
    private String policyName;

    @NotBlank(message = "Policy Type is required")
    private String policyType;

    @NotNull(message = "Premium Amount is required")
    private Double premiumAmount;

    @NotNull(message = "Coverage Amount is required")
    private Double coverageAmount;

    @NotNull(message = "Duration is required")
    private Integer durationInYears;

    private String description;

    @NotBlank(message = "Status is required")
    private String status;
}