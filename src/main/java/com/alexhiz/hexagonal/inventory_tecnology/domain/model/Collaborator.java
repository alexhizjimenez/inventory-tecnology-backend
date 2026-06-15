package com.alexhiz.hexagonal.inventory_tecnology.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Collaborator {
    @EqualsAndHashCode.Include
    private UUID id;
    private String fullName;
    private String code;
    private AreaCollaborator area;
    private PositionCollaborator position;
    private LocalDateTime createdAt;
}
