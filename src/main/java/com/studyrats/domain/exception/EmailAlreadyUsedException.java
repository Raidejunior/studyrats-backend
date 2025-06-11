package com.studyrats.domain.exception;

public class EmailAlreadyUsedException extends UserValidationException {
    public EmailAlreadyUsedException(String email) {
        super("Email already in use: " + email);
    }
}
