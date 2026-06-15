package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.assignments;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.collaborators.CollaboratorPersistenceMapper;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products.ProductPersistenceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssignmentPersistenceMapper {

    private final ProductPersistenceMapper productMapper;
    private final CollaboratorPersistenceMapper collaboratorMapper;

    AssignmentEntity toEntity(Assignment assignment) {
        return AssignmentEntity.builder()
                .id(assignment.getId())
                .product(productMapper.toEntity(assignment.getProduct()))
                .collaborator(collaboratorMapper.toEntity(assignment.getCollaborator()))
                .deliveryDate(assignment.getDeliveryDate())
                .returnedDate(assignment.getReturnedDate())
                .status(assignment.getStatus())
                .createdAt(assignment.getCreatedAt())
                .build();
    }

    Assignment toDomain(AssignmentEntity assignmentEntity) {
        return Assignment.builder()
                .id(assignmentEntity.getId())
                .product(productMapper.toDomain(assignmentEntity.getProduct()))
                .collaborator(collaboratorMapper.toDomain(assignmentEntity.getCollaborator()))
                .deliveryDate(assignmentEntity.getDeliveryDate())
                .returnedDate(assignmentEntity.getReturnedDate())
                .status(assignmentEntity.getStatus())
                .createdAt(assignmentEntity.getCreatedAt())
                .build();
    }

}
