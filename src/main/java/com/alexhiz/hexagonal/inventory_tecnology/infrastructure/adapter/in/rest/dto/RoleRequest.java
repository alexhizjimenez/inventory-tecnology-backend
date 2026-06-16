package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequest {
    private RolesUser name;
}
