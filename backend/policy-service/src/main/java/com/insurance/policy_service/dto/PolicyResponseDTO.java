package com.insurance.policy_service.dto;

import lombok.Data;

@Data
public class PolicyResponseDTO {

    private Long policyId;

    private String policyNumber;

    private String policyName;

    private String policyType;

    private Double premiumAmount;

    private Double coverageAmount;

    private Integer durationInYears;

    private String description;

    private String status;
}