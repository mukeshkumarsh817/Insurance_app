package com.insurance.claim_service.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

    private final ClaimRepository claimRepository;
    private final ModelMapper modelMapper;

    public ClaimServiceImpl(ClaimRepository claimRepository,
                            ModelMapper modelMapper) {
        this.claimRepository = claimRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ClaimResponseDTO addClaim(ClaimRequestDTO request) {

        if (claimRepository.existsByClaimNumber(request.getClaimNumber())) {
            throw new DuplicateClaimException("Claim Number already exists");
        }

        Claim claim = modelMapper.map(request, Claim.class);

        Claim savedClaim = claimRepository.save(claim);

        return modelMapper.map(savedClaim, ClaimResponseDTO.class);
    }

    @Override
    public List<ClaimResponseDTO> getAllClaims() {

        return claimRepository.findAll()
                .stream()
                .map(claim -> modelMapper.map(claim, ClaimResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClaimResponseDTO getClaimById(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimNotFoundException("Claim not found with id : " + claimId));

        return modelMapper.map(claim, ClaimResponseDTO.class);
    }

    @Override
    public ClaimResponseDTO updateClaim(Long claimId,
                                        ClaimRequestDTO request) {

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

        return modelMapper.map(updatedClaim, ClaimResponseDTO.class);
    }

    @Override
    public void deleteClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() ->
                        new ClaimNotFoundException("Claim not found with id : " + claimId));

        claimRepository.delete(claim);
    }
}