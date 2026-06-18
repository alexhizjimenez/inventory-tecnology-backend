package com.alexhiz.hexagonal.inventory_tecnology.application.services;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.CreateCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.GetCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.ListCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.CollaboratorRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.CollaboratorNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;


@RequiredArgsConstructor
public class CollaboratorService implements CreateCollaboratorUseCase, GetCollaboratorUseCase, ListCollaboratorUseCase {

    private final CollaboratorRepositoryPort collaboratorRepositoryPort;

    @Override
    public Collaborator create(Collaborator collaborator) {
        return collaboratorRepositoryPort.save(collaborator);
    }

    @Override
    public Collaborator getById(UUID id) {
        return collaboratorRepositoryPort.findById(id).orElseThrow(()-> new CollaboratorNotFoundException("No se encontro Colaborador" + id));
    }

    @Override
    public List<Collaborator> listAll() {
        return collaboratorRepositoryPort.findAll();
    }
}
