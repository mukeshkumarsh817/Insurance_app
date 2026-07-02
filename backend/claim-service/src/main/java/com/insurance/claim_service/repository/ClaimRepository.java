package com.insurance.claim_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.claim_service.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    boolean existsByClaimNumber(String claimNumber);

    Optional<Claim> findByClaimNumber(String claimNumber);

}