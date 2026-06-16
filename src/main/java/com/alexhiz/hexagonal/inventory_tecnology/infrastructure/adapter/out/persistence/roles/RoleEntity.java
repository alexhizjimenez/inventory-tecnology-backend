package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.roles;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private RolesUser name;
}
