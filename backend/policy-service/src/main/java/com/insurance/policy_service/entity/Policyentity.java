package com.insurance.policy_service.entity;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "policy")
@Data
public class Policyentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    @Column(nullable = false, unique = true)
    private String policyNumber;

    @Column(nullable = false)
    private String policyName;

    @Column(nullable = false)
    private String policyType;

    @Column(nullable = false)
    private Double premiumAmount;

    @Column(nullable = false)
    private Double coverageAmount;

    @Column(nullable = false)
    private Integer durationInYears;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private String status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @PrePersist
    public void onCreate() {
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}