package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class RoleResponse {
    private UUID id;
    private RolesUser name;

    public static RoleResponse from(Role role) {
        if (role == null) return null;
        return RoleResponse.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
