package com.insurance.policy_service.exception;

public class DuplicatePolicyException extends RuntimeException {

    public DuplicatePolicyException(String message) {
        super(message);
    }
}