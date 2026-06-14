package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.assignments;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, UUID> {
}
