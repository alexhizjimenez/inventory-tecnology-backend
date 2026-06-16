package com.alexhiz.hexagonal.inventory_tecnology.domain.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Role {
    @EqualsAndHashCode.Include
    private UUID id;
    private RolesUser name;
}
