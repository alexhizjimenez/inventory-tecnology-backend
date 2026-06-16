package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<UserEntity, UUID> {
}
