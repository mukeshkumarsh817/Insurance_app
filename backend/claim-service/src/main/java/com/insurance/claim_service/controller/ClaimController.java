package com.insurance.claim_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurance.claim_service.dto.ClaimRequestDTO;
import com.insurance.claim_service.dto.ClaimResponseDTO;
import com.insurance.claim_service.service.ClaimService;

import jakarta.validation.Valid;
//import jakarta.validation.Valid;
//System.out.println("rohit kumar");
@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Create Claim
    @PostMapping
    public ResponseEntity<ClaimResponseDTO> addClaim(
            @Valid @RequestBody ClaimRequestDTO request) {
        
        ClaimResponseDTO response = claimService.addClaim(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Get All Claims
    @GetMapping
    public ResponseEntity<List<ClaimResponseDTO>> getAllClaims() {
       
        return ResponseEntity.ok(claimService.getAllClaims());
    }

    // Get Claim By Id
    @GetMapping("/{claimId}")
    public ResponseEntity<ClaimResponseDTO> getClaimById(
            @PathVariable Long claimId) {

        return ResponseEntity.ok(claimService.getClaimById(claimId));
    }

    // Update Claim
    @PutMapping("/{claimId}")
    public ResponseEntity<ClaimResponseDTO> updateClaim(
            @PathVariable Long claimId,
            @Valid @RequestBody ClaimRequestDTO request) {

        return ResponseEntity.ok(
                claimService.updateClaim(claimId, request));
    }

    // Delete Claim
    @DeleteMapping("/{claimId}")
    public ResponseEntity<String> deleteClaim(
            @PathVariable Long claimId) {

        claimService.deleteClaim(claimId);

        return ResponseEntity.ok("Claim deleted successfully.");
    }
}