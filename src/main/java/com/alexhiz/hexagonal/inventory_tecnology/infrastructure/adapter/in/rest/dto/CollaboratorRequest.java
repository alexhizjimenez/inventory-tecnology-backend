package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.AreaCollaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.PositionCollaborator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollaboratorRequest {
    private String fullName;
    private AreaCollaborator area;
    private PositionCollaborator position;
}
