package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusAssignment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AssignmentStatusRequest {
    private StatusAssignment status;
}
