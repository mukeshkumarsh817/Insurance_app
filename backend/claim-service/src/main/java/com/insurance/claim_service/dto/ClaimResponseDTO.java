package com.insurance.claim_service.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ClaimResponseDTO {

    private Long claimId;
    private String claimNumber;
    private Long userId;
    private Long policyId;
    private Double claimAmount;
    private String claimReason;
    private String claimStatus;
    private LocalDateTime claimDate;
}