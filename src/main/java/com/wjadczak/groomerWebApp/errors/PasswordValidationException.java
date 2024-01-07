package com.wjadczak.groomerWebApp.errors;

public class PasswordValidationException extends RuntimeException {
    public PasswordValidationException(String message) {
        super(message);
    }
}