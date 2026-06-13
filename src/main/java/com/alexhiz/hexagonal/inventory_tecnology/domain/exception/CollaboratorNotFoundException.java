package com.alexhiz.hexagonal.inventory_tecnology.domain.exception;

public class CollaboratorNotFoundException extends RuntimeException {

    public CollaboratorNotFoundException(String message) {
        super(message);
    }

}
