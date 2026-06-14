package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;

import java.util.UUID;

public interface GetAssignmentUseCase {
    Assignment getById(UUID id);
}
