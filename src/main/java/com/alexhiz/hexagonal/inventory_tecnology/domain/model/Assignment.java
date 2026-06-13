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
public class Assignment {
    @EqualsAndHashCode.Include
    private UUID id;
    private Product product;
    private Collaborator collaborator;
    private LocalDateTime deliveryDate;
    private StatusAssignment status;
    private LocalDateTime createdAt;
}
