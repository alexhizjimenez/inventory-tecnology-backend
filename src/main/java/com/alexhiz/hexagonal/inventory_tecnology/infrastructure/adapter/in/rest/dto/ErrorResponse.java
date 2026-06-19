package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime date,
        String message,
        String error,
        String status,
        List<String> list
) {

    public ErrorResponse(String message, String error, String status) {
        this(LocalDateTime.now(), message, error, status, List.of());
    }

    public ErrorResponse(String message, String error, String status, List<String> list) {
        this(LocalDateTime.now(), message, error, status, list);
    }
}
