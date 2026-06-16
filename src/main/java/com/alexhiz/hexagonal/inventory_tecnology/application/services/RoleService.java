package com.alexhiz.hexagonal.inventory_tecnology.application.services;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role.CreateRoleUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role.GetRoleUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role.ListRoleUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.RoleRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.RoleNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService implements CreateRoleUseCase, GetRoleUseCase, ListRoleUseCase {

    private final RoleRepositoryPort roleRepositoryPort;

    @Override
    public Role create(Role role) {
        return roleRepositoryPort.save(role);
    }

    @Override
    public Role findById(UUID id) {
        return roleRepositoryPort.findById(id).orElseThrow(() -> new RoleNotFoundException("Role not found" + id));
    }

    @Override
    public List<Role> findAll() {
        return roleRepositoryPort.findAll();
    }
}
