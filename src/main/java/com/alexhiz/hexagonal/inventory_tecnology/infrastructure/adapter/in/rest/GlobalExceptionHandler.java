package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.AssignmentNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.CollaboratorNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.ProductNotFoundException;

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
