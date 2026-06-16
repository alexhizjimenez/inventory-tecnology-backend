package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.CreateCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.GetCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.ListCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.CollaboratorRequest;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.CollaboratorResponse;
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
@RequestMapping("/api/v1/collaborators")
@RequiredArgsConstructor
public class CollaboratorController {

    private final CreateCollaboratorUseCase createCollaboratorUseCase;
    private final GetCollaboratorUseCase getCollaboratorUseCase;
    private final ListCollaboratorUseCase listCollaboratorUseCase;

    @Operation(
            summary = "Crea calaborador",
            description = "Retorna la información de un calaborador creado"
    )
    @ApiResponse(responseCode = "200", description = "Calaborador creado")
    @ApiResponse(responseCode = "404", description = "Error al crear calaborador")
    @PostMapping
    private ResponseEntity<CollaboratorResponse> createCollaborator(@Valid  @RequestBody CollaboratorRequest collaboratorRequest) {
        Collaborator collaborator = Collaborator.builder()
                .fullName(collaboratorRequest.getFullName())
                .code(collaboratorRequest.getCode())
                .area(collaboratorRequest.getArea())
                .position(collaboratorRequest.getPosition())
                .build();
        Collaborator saved = createCollaboratorUseCase.create(collaborator);
        return ResponseEntity.status(HttpStatus.CREATED).body(CollaboratorResponse.from(saved));
    }


    @Operation(
            summary = "Lista de colaboradores",
            description = "Retorna la lista de colaboradores"
    )
    @ApiResponse(responseCode = "200", description = "Todos los colaboradores")
    @ApiResponse(responseCode = "404", description = "Error al obtener colaboradores")
    @GetMapping
    public ResponseEntity<List<CollaboratorResponse>> findAll() {
        List<CollaboratorResponse> collaborators =  listCollaboratorUseCase.listAll().stream().map(CollaboratorResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(collaborators);
    }

    @Operation(
            summary = "Obtener calaborador por ID",
            description = "Retorna la información de un calaborador"
    )
    @ApiResponse(responseCode = "200", description = "Calaborador encontrado")
    @ApiResponse(responseCode = "404", description = "Calaborador no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<CollaboratorResponse> getCollaborator(@PathVariable UUID id) {
        Collaborator collaborator = getCollaboratorUseCase.getById(id);
        return  ResponseEntity.ok(CollaboratorResponse.from(collaborator));
    }

}
