package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;

public interface CreateUserUserCase {
    User create(User user);
}
