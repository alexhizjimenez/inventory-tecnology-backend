package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;

import java.util.List;

public interface ListAssignmentUseCase {
    List<Assignment> listAll();
}
