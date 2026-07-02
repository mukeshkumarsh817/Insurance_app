package com.insurance.claim_service.exception;

public class ClaimNotFoundException extends RuntimeException {

    public ClaimNotFoundException(String message) {
        super(message);
    }
}