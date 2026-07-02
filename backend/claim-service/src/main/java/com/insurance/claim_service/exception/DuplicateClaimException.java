package com.insurance.claim_service.exception;

public class DuplicateClaimException extends RuntimeException {

    public DuplicateClaimException(String message) {
        super(message);
    }
}