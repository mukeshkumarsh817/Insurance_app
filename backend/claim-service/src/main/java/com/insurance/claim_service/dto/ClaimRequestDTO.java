package com.insurance.claim_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClaimRequestDTO {

    @NotBlank(message = "Claim Number is required")
    private String claimNumber;

    @NotNull(message = "User Id is required")
    private Long userId;

    @NotNull(message = "Policy Id is required")
    private Long policyId;

    @NotNull(message = "Claim Amount is required")
    private Double claimAmount;

    @NotBlank(message = "Claim Reason is required")
    private String claimReason;

    @NotBlank(message = "Claim Status is required")
    private String claimStatus;

    //private String claimDate; // Optional field, can be null
}