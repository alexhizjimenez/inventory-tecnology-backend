package com.alexhiz.hexagonal.inventory_tecnology.application.port.out;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    User save(User user);
    List<User> findAll();
    Optional<User> findById(UUID id);
}
