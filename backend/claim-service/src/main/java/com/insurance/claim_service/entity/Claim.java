package com.insurance.claim_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "claim")
@Data
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimId;

    @Column(nullable = false, unique = true)
    private String claimNumber;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long policyId;

    @Column(nullable = false)
    private Double claimAmount;

    @Column(nullable = false)
    private String claimReason;

    @Column(nullable = false)
    private String claimStatus;

    private LocalDateTime claimDate;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    @PrePersist
    public void onCreate() {
        claimDate = LocalDateTime.now();
        createdDate = LocalDateTime.now();
        updatedDate = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}