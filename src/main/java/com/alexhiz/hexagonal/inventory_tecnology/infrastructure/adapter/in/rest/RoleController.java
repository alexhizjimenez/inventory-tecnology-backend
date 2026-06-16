package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role.CreateRoleUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role.GetRoleUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.role.ListRoleUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Role;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.RoleRequest;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.RoleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
public class RoleController {

    private final CreateRoleUseCase createRoleUseCase;
    private final ListRoleUseCase listRoleUseCase;
    private final GetRoleUseCase getRoleUseCase;

    @Operation(
            summary = "Crea un rol",
            description = "Retorna la información de un rol creado"
    )
    @ApiResponse(responseCode = "201", description = "Rol creado")
    @PostMapping
    public ResponseEntity<RoleResponse> create(@Valid @RequestBody RoleRequest request) {
        Role role = Role.builder()
                .name(request.getName())
                .build();
        Role created = createRoleUseCase.create(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoleResponse.from(created));
    }

    @Operation(
            summary = "Lista todos los roles",
            description = "Retorna una lista de todos los roles"
    )
    @ApiResponse(responseCode = "200", description = "Lista de roles")
    @GetMapping
    public ResponseEntity<List<RoleResponse>> findAll() {
        List<RoleResponse> roles = listRoleUseCase.findAll().stream()
                .map(RoleResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(roles);
    }

    @Operation(
            summary = "Obtener rol por ID",
            description = "Retorna la información de un rol"
    )
    @ApiResponse(responseCode = "200", description = "Rol encontrado")
    @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<RoleResponse> findById(@PathVariable UUID id) {
        Role role = getRoleUseCase.findById(id);
        return ResponseEntity.ok(RoleResponse.from(role));
    }
}
