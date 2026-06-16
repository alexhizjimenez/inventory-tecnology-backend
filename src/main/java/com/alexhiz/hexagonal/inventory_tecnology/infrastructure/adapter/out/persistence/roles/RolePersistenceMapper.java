package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.roles;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RolePersistenceMapper {

    public Role toDomain(RoleEntity entity) {
        if (entity == null) return null;
        return Role.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public RoleEntity toEntity(Role domain) {
        if (domain == null) return null;
        return RoleEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .build();
    }

    public Set<Role> toDomainSet(Set<RoleEntity> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    public Set<RoleEntity> toEntitySet(Set<Role> domains) {
        if (domains == null) return null;
        return domains.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }
}
