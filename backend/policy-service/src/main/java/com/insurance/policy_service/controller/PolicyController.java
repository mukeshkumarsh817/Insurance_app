package com.insurance.policy_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.insurance.policy_service.dto.PolicyRequestDTO;
import com.insurance.policy_service.dto.PolicyResponseDTO;
import com.insurance.policy_service.service.PolicyService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    public ResponseEntity<PolicyResponseDTO> addPolicy(
            @Valid @RequestBody PolicyRequestDTO request) {

        PolicyResponseDTO response = policyService.addPolicy(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
public ResponseEntity<List<PolicyResponseDTO>> getAllPolicies() {

    List<PolicyResponseDTO> policies = policyService.getAllPolicies();

    return ResponseEntity.ok(policies);
}

 @GetMapping("/{policyId}")
public ResponseEntity<PolicyResponseDTO> getPolicyById(
        @PathVariable Long policyId) {

    PolicyResponseDTO response = policyService.getPolicyById(policyId);

    return ResponseEntity.ok(response);
}

@DeleteMapping("/{policyId}")
public ResponseEntity<String> deletePolicy(@PathVariable Long policyId) {

    policyService.deletePolicy(policyId);

    return ResponseEntity.ok("Policy deleted successfully");
}

}