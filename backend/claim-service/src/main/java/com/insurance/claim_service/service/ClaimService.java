
package com.insurance.claim_service.service;

import java.util.List;

import com.insurance.claim_service.dto.ClaimRequestDTO;
import com.insurance.claim_service.dto.ClaimResponseDTO;

public interface ClaimService {

    // Create Claim
    ClaimResponseDTO addClaim(ClaimRequestDTO request);

    // Get All Claims
    List<ClaimResponseDTO> getAllClaims();

    // Get Claim By Id
    ClaimResponseDTO getClaimById(Long claimId);

    // Update Claim
    ClaimResponseDTO updateClaim(Long claimId, ClaimRequestDTO request);

    // Delete Claim
    void deleteClaim(Long claimId);

    //void deleteAllClaims();
}