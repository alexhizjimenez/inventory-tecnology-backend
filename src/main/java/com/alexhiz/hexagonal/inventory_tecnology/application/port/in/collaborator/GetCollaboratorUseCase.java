package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;

import java.util.UUID;

public interface GetCollaboratorUseCase {
    Collaborator getById(UUID id);
}
