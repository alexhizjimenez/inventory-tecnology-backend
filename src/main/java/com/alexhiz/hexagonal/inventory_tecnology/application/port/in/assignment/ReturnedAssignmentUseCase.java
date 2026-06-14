package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusAssignment;

import java.util.UUID;

public interface ReturnedAssignmentUseCase {
    Assignment returnedProductToAssignment(UUID id, StatusAssignment status);
}
