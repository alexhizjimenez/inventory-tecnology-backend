package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;

public interface CreateRoleUseCase {
    Role create(Role role);
}
