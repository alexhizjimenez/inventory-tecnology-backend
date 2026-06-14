package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.CreateAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.GetAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.ListAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.ReturnedAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.AssignmentRequest;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.AssignmentResponse;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.AssignmentStatusRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/assignments")
@RequiredArgsConstructor
public class AssignmentController {

    private final CreateAssignmentUseCase createAssignmentUseCase;
    private final GetAssignmentUseCase getAssignmentUseCase;
    private final ListAssignmentUseCase listAssignmentUseCase;
    private final ReturnedAssignmentUseCase returnedAssignmentUseCase;

    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(@Valid @RequestBody AssignmentRequest assignmentRequest) {
        System.out.println("Assignment Request: " + assignmentRequest);
        Assignment assignment = Assignment.builder()
                .product(Product.builder().id(assignmentRequest.getProductId()).build())
                .collaborator(Collaborator.builder().id(assignmentRequest.getCollaboratorId()).build())
                .status(assignmentRequest.getStatus())
                .build();
        Assignment saved = createAssignmentUseCase.create(assignment);
        return ResponseEntity.status(HttpStatus.CREATED).body(AssignmentResponse.from(saved));
    }

    @GetMapping
    public ResponseEntity<List<AssignmentResponse>> getAllAssignments() {
        List<AssignmentResponse> assignmentResponse = listAssignmentUseCase.listAll().stream().map(AssignmentResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(assignmentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> getAssignment(@PathVariable UUID id) {
        Assignment assignment = getAssignmentUseCase.getById(id);
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<AssignmentResponse> getAssignmentStatus(@Valid @PathVariable UUID id, @RequestBody AssignmentStatusRequest assignmentStatusRequest) {
        Assignment assignment = returnedAssignmentUseCase.returnedProductToAssignment(id, assignmentStatusRequest.getStatus());
        return ResponseEntity.ok(AssignmentResponse.from(assignment));
    }


}
