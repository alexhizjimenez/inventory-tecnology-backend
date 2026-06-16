package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;

import java.util.UUID;

public interface GetUserUseCase {
    User getById(UUID id);
}
