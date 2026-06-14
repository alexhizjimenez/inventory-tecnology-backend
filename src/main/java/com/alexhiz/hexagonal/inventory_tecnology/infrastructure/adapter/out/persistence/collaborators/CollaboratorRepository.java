package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.collaborators;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollaboratorRepository extends JpaRepository<CollaboratorEntity, UUID> {
}
