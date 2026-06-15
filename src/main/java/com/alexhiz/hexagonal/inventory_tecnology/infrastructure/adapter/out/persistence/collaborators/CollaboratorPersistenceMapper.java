package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.collaborators;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class CollaboratorPersistenceMapper {



    public Collaborator toDomain(CollaboratorEntity collaboratorEntity) {
        if(collaboratorEntity == null) return null;
        return Collaborator.builder()
                .id(collaboratorEntity.getId())
                .fullName(collaboratorEntity.getFullName())
                .code(collaboratorEntity.getCode())
                .area(collaboratorEntity.getArea())
                .position(collaboratorEntity.getPosition())
                .createdAt(collaboratorEntity.getCreatedAt())
                .build();
    }

    public CollaboratorEntity toEntity(Collaborator collaborator) {
        if (collaborator == null) return null;
        return CollaboratorEntity.builder()
                .id(collaborator.getId())
                .fullName(collaborator.getFullName())
                .code(collaborator.getCode())
                .area(collaborator.getArea())
                .position(collaborator.getPosition())
                .createdAt(collaborator.getCreatedAt())
                .build();
    }
}
