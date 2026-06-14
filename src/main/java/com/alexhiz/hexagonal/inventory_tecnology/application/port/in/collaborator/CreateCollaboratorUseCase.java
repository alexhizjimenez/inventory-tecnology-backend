package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;

public interface CreateCollaboratorUseCase {
    Collaborator create(Collaborator collaborator);
}
