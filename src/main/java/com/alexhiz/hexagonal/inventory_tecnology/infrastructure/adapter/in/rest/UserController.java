package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user.CreateUserUserCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user.GetUserUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.user.ListUserUserCase;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.User;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.UserRequest;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUserCase createUserUserCase;
    private final ListUserUserCase listUserUserCase;
    private final GetUserUseCase getUserUseCase;


    @Operation(
            summary = "Crea usuario",
            description = "Retorna la información de un usuario creado"
    )
    @ApiResponse(responseCode = "201", description = "Usuario creado")
    @PostMapping
    public ResponseEntity<UserResponse> save(@Valid @RequestBody UserRequest request) {
        Set<Role> roles = request.getRoleIds().stream()
                .map(id -> Role.builder().id(id).build())
                .collect(Collectors.toSet());

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(roles)
                .build();
        User created = createUserUserCase.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(created));
    }

    @Operation(
            summary = "Lista de usuarios",
            description = "Retorna la lista de usuarios"
    )
    @ApiResponse(responseCode = "200", description = "Todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        List<UserResponse> users = listUserUserCase.list().stream().map(UserResponse::from ).collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }


    @Operation(
            summary = "Obtener usuario por ID",
            description = "Retorna la información de un usuario"
    )
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable UUID id) {
        User user = getUserUseCase.getById(id);
        return ResponseEntity.ok(UserResponse.from(user));
    }

}
