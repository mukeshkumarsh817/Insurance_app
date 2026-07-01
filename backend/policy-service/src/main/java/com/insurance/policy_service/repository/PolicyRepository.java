package com.insurance.policy_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurance.policy_service.entity.Policyentity;

public interface PolicyRepository extends JpaRepository<Policyentity, Long> {

    Optional<Policyentity> findByPolicyNumber(String policyNumber);

    boolean existsByPolicyNumber(String policyNumber);

}