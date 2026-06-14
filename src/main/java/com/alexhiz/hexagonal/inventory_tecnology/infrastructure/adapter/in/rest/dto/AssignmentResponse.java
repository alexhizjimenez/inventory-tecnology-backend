package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusAssignment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class AssignmentResponse {
    private UUID id;
    private Product product;
    private Collaborator collaborator;
    private LocalDateTime deliveryDate;
    private StatusAssignment status;
    private LocalDateTime createdAt;

    public static AssignmentResponse from(Assignment assignment) {
        return AssignmentResponse.builder()
                .id(assignment.getId())
                .product(assignment.getProduct())
                .collaborator(assignment.getCollaborator())
                .deliveryDate(assignment.getDeliveryDate())
                .status(assignment.getStatus())
                .createdAt(assignment.getCreatedAt())
                .build();
    }
}
