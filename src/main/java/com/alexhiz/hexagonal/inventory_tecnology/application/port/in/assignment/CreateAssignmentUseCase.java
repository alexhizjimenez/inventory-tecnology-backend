package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;

public interface CreateAssignmentUseCase {
    Assignment create(Assignment assignment);
}
