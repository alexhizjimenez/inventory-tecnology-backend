package com.alexhiz.hexagonal.inventory_tecnology.domain.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
