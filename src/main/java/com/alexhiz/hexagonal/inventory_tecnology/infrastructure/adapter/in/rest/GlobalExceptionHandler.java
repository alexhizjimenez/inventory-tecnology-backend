package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import java.util.List;

import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ProblemDetail handleProductNotFound(ProductNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problem.setTitle("Producto no encontrado");
        return problem;
    }

    @ExceptionHandler(ProductWithOutStockException.class)
    public ProblemDetail handleProductWithOutStock(ProductWithOutStockException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );
        problem.setTitle("Producto sin stock");
        problem.setProperty("errorCode", "PRODUCT_OUT_OF_STOCK");
        return problem;
    }

    @ExceptionHandler(ProductInvalidStockException.class)
    public ProblemDetail handleProductInvalidStock(ProductInvalidStockException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, ex.getMessage()
        );
        problem.setTitle("Producto stock invalido, stock negativo");
        problem.setProperty("errorCode", "PRODUCT_STOCK_INVALID");
        return problem;
    }

    @ExceptionHandler(CollaboratorNotFoundException.class)
    public ProblemDetail handleCollaboratorNotFound(CollaboratorNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problem.setTitle("Colaborador no encontrado");
        return problem;
    }

    @ExceptionHandler(AssignmentNotFoundException.class)
    public ProblemDetail handleAssignmentNotFound(AssignmentNotFoundException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND, ex.getMessage()
        );
        problem.setTitle("Asignación no encontrada");
        return problem;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationErrors(MethodArgumentNotValidException ex) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST, "Error de validación"
        );
        problem.setTitle("Datos inválidos");
        problem.setProperty("errors", errors);
        return problem;
    }

}
