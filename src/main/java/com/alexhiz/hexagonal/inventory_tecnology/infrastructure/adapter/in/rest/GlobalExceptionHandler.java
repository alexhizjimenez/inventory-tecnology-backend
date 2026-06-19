package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import java.time.LocalDateTime;
import java.util.List;

import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.*;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                status.getReasonPhrase(),
                String.valueOf(status.value())
                );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ProductWithOutStockException.class)
    public ResponseEntity<ErrorResponse> handleProductWithOutStock(ProductWithOutStockException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                status.getReasonPhrase(),
                String.valueOf(status.value())
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(ProductInvalidStockException.class)
    public ResponseEntity<ErrorResponse> handleProductInvalidStock(ProductInvalidStockException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                status.getReasonPhrase(),
                String.valueOf(status.value())
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(CollaboratorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCollaboratorNotFound(CollaboratorNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                status.getReasonPhrase(),
                String.valueOf(status.value())
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAssignmentNotFound(AssignmentNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                status.getReasonPhrase(),
                String.valueOf(status.value())
        );
        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                status.getReasonPhrase(),
                String.valueOf(status.value()),
                errors
        );
        return ResponseEntity.status(status).body(response);
    }

}
