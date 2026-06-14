package com.alexhiz.hexagonal.inventory_tecnology.domain.exception;

public class ProductInvalidStockException extends RuntimeException {
    public ProductInvalidStockException(String message) {
        super(message);
    }
}
