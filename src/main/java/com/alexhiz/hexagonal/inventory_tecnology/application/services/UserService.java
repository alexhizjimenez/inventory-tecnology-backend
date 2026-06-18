package com.alexhiz.hexagonal.inventory_tecnology.application.services;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user.CreateUserUserCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user.GetUserUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user.ListUserUserCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.PasswordEncoderPort;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.RoleRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.UserRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.RoleNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.UserNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.RolesUser;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUserCase, GetUserUseCase, ListUserUserCase {

    private final UserRepositoryPort userRepositoryPort;
    private final RoleRepositoryPort roleRepositoryPort;
    private final PasswordEncoderPort passwordEncoderPort;

    @Override
    public User create(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {

            Role adminRole = roleRepositoryPort.findByName(RolesUser.ADMIN)
                    .orElseGet(() -> roleRepositoryPort.save(
                            Role.builder()
                                    .name(RolesUser.ADMIN)
                                    .build()
                    ));

            user.setRoles(Set.of(adminRole));

        } else {

            Set<Role> fullRoles = user.getRoles().stream()
                    .map(role -> roleRepositoryPort.findById(role.getId())
                            .orElseThrow(() ->
                                    new RoleNotFoundException(
                                            "Role not found with ID: " + role.getId())))
                    .collect(Collectors.toSet());

            user.setRoles(fullRoles);
        }

        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        return userRepositoryPort.save(user);
    }

    @Override
    public User getById(UUID id) {
        return userRepositoryPort.findById(id).orElseThrow(()-> new UserNotFoundException("User not found" + id));
    }

    @Override
    public List<User> list() {
        return userRepositoryPort.findAll();
    }
}
