package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.collaborators;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.CollaboratorRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CollaboratorPersistenceAdapter  implements CollaboratorRepositoryPort {

    private final CollaboratorRepository repository;
    private final CollaboratorPersistenceMapper mapper;

    @Override
    public Collaborator save(Collaborator collaborator) {
        CollaboratorEntity entity = mapper.toEntity(collaborator);
        CollaboratorEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Collaborator> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Collaborator> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }
}
