package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(RolesUser name);
}
