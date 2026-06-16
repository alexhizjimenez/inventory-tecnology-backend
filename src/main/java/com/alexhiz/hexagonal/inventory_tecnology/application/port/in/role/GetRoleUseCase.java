package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;

import java.util.UUID;

public interface GetRoleUseCase {
    Role findById(UUID id);
}
