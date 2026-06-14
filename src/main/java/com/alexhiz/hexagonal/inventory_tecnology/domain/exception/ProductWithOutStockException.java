package com.alexhiz.hexagonal.inventory_tecnology.domain.exception;

public class ProductWithOutStockException extends RuntimeException {
    public ProductWithOutStockException(String message) {
        super(message);
    }
}
