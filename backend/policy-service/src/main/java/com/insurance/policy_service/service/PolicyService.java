package com.insurance.policy_service.service;

import java.util.List;

import com.insurance.policy_service.dto.PolicyRequestDTO;
import com.insurance.policy_service.dto.PolicyResponseDTO;

public interface PolicyService {

    PolicyResponseDTO addPolicy(PolicyRequestDTO request);

    List<PolicyResponseDTO> getAllPolicies();

    PolicyResponseDTO getPolicyById(Long policyId);

    PolicyResponseDTO updatePolicy(Long policyId, PolicyRequestDTO request);

    void deletePolicy(Long policyId);
}