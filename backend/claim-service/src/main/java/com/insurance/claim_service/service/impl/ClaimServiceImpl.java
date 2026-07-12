package com.insurance.claim_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.insurance.claim_service.dto.ClaimRequestDTO;
import com.insurance.claim_service.dto.ClaimResponseDTO;
import com.insurance.claim_service.entity.Claim;
import com.insurance.claim_service.exception.ClaimNotFoundException;
import com.insurance.claim_service.exception.DuplicateClaimException;
import com.insurance.claim_service.repository.ClaimRepository;
import com.insurance.claim_service.service.ClaimService;

@Service
public class ClaimServiceImpl implements ClaimService {

    private static final Logger logger =
            LoggerFactory.getLogger(ClaimServiceImpl.class);

    private final ClaimRepository claimRepository;
    private final ModelMapper modelMapper;

    public ClaimServiceImpl(ClaimRepository claimRepository,
                            ModelMapper modelMapper) {
        this.claimRepository = claimRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClaimResponseDTO addClaim(ClaimRequestDTO request) {

        logger.info("Creating new claim with claim number: {}", request.getClaimNumber());

        if (claimRepository.existsByClaimNumber(request.getClaimNumber())) {
            throw new DuplicateClaimException("Claim Number already exists");
        }

        Claim claim = modelMapper.map(request, Claim.class);

        Claim savedClaim = claimRepository.save(claim);
        //System.out.println("Hi Mukesh");

        logger.info("Claim created successfully with ID: {}", savedClaim.getClaimId());

        return modelMapper.map(savedClaim, ClaimResponseDTO.class);
    }

    @Override
    public List<ClaimResponseDTO> getAllClaims() {

        logger.info("Fetching all claims");

        return claimRepository.findAll()
                .stream()
                .map(claim -> modelMapper.map(claim, ClaimResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClaimResponseDTO getClaimById(Long claimId) {

        logger.info("Fetching claim with ID: {}", claimId);

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimNotFoundException("Claim not found with id : " + claimId));

        return modelMapper.map(claim, ClaimResponseDTO.class);
    }

    @Override
    public ClaimResponseDTO updateClaim(Long claimId,
                                        ClaimRequestDTO request) {

        logger.info("Updating claim with ID: {}", claimId);

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimNotFoundException("Claim not found with id : " + claimId));

        claim.setClaimNumber(request.getClaimNumber());
        claim.setUserId(request.getUserId());
        claim.setPolicyId(request.getPolicyId());
        claim.setClaimAmount(request.getClaimAmount());
        claim.setClaimReason(request.getClaimReason());
        claim.setClaimStatus(request.getClaimStatus());

        Claim updatedClaim = claimRepository.save(claim);

        logger.info("Claim updated successfully with ID: {}", updatedClaim.getClaimId());

        return modelMapper.map(updatedClaim, ClaimResponseDTO.class);
    }

    @Override
    public void deleteClaim(Long claimId) {

        logger.info("Deleting claim with ID: {}", claimId);

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimNotFoundException("Claim not found with id : " + claimId));

        claimRepository.delete(claim);

        logger.info("Claim deleted successfully with ID: {}", claimId);
    }
}