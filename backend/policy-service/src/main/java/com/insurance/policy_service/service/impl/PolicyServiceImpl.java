package com.insurance.policy_service.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insurance.policy_service.dto.PolicyRequestDTO;
import com.insurance.policy_service.dto.PolicyResponseDTO;
import com.insurance.policy_service.entity.Policyentity;
import com.insurance.policy_service.exception.DuplicatePolicyException;
import com.insurance.policy_service.exception.PolicyNotFoundException;
import com.insurance.policy_service.repository.PolicyRepository;
import com.insurance.policy_service.service.PolicyService;
import com.insurance.policy_service.exception.PolicyNotFoundException;
import com.insurance.policy_service.exception.DuplicatePolicyException;

@Service
public class PolicyServiceImpl implements PolicyService {

    private static final Logger logger =
        LoggerFactory.getLogger(PolicyServiceImpl.class);

    private final PolicyRepository policyRepository;
    private final ModelMapper modelMapper;

    public PolicyServiceImpl(PolicyRepository policyRepository,
                             ModelMapper modelMapper) {
        this.policyRepository = policyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PolicyResponseDTO addPolicy(PolicyRequestDTO request) {

        if (policyRepository.existsByPolicyNumber(request.getPolicyNumber())) {
            logger.warn("Duplicate policy number: {}", request.getPolicyNumber());
            throw new  DuplicatePolicyException("Policy Number already exists");
        }
        
        logger.info("Creating policy with policy number: {}", request.getPolicyNumber());
        Policyentity policy = modelMapper.map(request, Policyentity.class);

        Policyentity savedPolicy = policyRepository.save(policy);
        logger.info("Policy created successfully with ID: {}", savedPolicy.getPolicyId());
        return modelMapper.map(savedPolicy, PolicyResponseDTO.class);
    }

    @Override
public List<PolicyResponseDTO> getAllPolicies() {

    List<Policyentity> policies = policyRepository.findAll();

    return policies.stream()
            .map(policy -> modelMapper.map(policy, PolicyResponseDTO.class))
            .toList();
}

    @Override
public PolicyResponseDTO getPolicyById(Long policyId) {

    Policyentity policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

    return modelMapper.map(policy, PolicyResponseDTO.class);
}

    @Override
public PolicyResponseDTO updatePolicy(Long policyId, PolicyRequestDTO request) {

    Policyentity policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));

    policy.setPolicyNumber(request.getPolicyNumber());
    policy.setPolicyName(request.getPolicyName());
    policy.setPolicyType(request.getPolicyType());
    policy.setPremiumAmount(request.getPremiumAmount());
    policy.setCoverageAmount(request.getCoverageAmount());
    policy.setDurationInYears(request.getDurationInYears());
    policy.setDescription(request.getDescription());
    policy.setStatus(request.getStatus());

    Policyentity updatedPolicy = policyRepository.save(policy);

    return modelMapper.map(updatedPolicy, PolicyResponseDTO.class);
}

    @Override
public void deletePolicy(Long policyId) {

    Policyentity policy = policyRepository.findById(policyId)
            .orElseThrow(() -> new PolicyNotFoundException("Policy not found"));
   logger.info("Deleting policy with ID: {}", policyId);
    policyRepository.delete(policy);
    logger.info("Policy deleted successfully.");
}
}