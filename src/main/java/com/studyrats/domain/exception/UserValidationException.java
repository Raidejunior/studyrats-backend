package com.studyrats.domain.exception;

public abstract class UserValidationException extends RuntimeException {
    protected UserValidationException(String message) {
        super(message);
    }
}
