package com.alexhiz.hexagonal.inventory_tecnology.application.port.out;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CollaboratorRepositoryPort {
    Collaborator save(Collaborator collaborator);
    List<Collaborator> findAll();
    Optional<Collaborator> findById(UUID id);
}
