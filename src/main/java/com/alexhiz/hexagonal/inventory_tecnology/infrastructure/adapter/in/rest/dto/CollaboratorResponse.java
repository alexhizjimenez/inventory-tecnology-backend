package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.AreaCollaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.PositionCollaborator;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class CollaboratorResponse {

    private UUID id;
    private String fullName;
    private AreaCollaborator area;
    private PositionCollaborator position;
    private LocalDateTime createdAt;

    public static CollaboratorResponse from(Collaborator collaborator) {
        return CollaboratorResponse.builder()
                .id(collaborator.getId())
                .fullName(collaborator.getFullName())
                .area(collaborator.getArea())
                .position(collaborator.getPosition())
                .createdAt(collaborator.getCreatedAt())
                .build();
    }
}
