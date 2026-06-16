package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.roles;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.RoleRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RolePersistenceAdapter implements RoleRepositoryPort {

    private final RoleRepository roleRepository;
    private final RolePersistenceMapper roleMapper;

    @Override
    public Role save(Role role) {
        RoleEntity entity = roleMapper.toEntity(role);
        return roleMapper.toDomain(roleRepository.save(entity));
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDomain);
    }

}
