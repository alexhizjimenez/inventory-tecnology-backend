package com.alexhiz.hexagonal.inventory_tecnology.application.port.out;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AssignmentRepositoryPort {
    Assignment save(Assignment assignment);
    Optional<Assignment> findById(UUID id);
    List<Assignment> findAll();
}
