package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.assignments;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.AssignmentRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AssignmentPersistenceAdapter implements AssignmentRepositoryPort {

    private final AssignmentRepository repository;
    private final AssignmentPersistenceMapper mapper;

    @Override
    public Assignment save(Assignment assignment) {
        AssignmentEntity assignmentEntity = mapper.toEntity(assignment);
        AssignmentEntity saved = repository.save(assignmentEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Assignment> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Assignment> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
