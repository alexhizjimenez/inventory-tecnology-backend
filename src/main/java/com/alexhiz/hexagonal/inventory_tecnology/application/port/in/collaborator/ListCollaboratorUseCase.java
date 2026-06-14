package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;

import java.util.List;

public interface ListCollaboratorUseCase {
    List<Collaborator> listAll();
}
