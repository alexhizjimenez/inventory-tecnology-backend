package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusAssignment;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class AssignmentRequest {
    @NotNull(message = "El id del producto es obligatorio")
    private UUID productId;

    @NotNull(message = "El id del colaborador es obligatorio")
    private UUID collaboratorId;

    private StatusAssignment status;
}
