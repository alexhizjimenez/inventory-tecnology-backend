package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.CreateCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.GetCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.collaborator.ListCollaboratorUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.CollaboratorRequest;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.CollaboratorResponse;
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

    @PostMapping
    private ResponseEntity<CollaboratorResponse> createCollaborator(@Valid  @RequestBody CollaboratorRequest collaboratorRequest) {
        Collaborator collaborator = Collaborator.builder()
                .fullName(collaboratorRequest.getFullName())
                .area(collaboratorRequest.getArea())
                .position(collaboratorRequest.getPosition())
                .build();
        Collaborator saved = createCollaboratorUseCase.create(collaborator);
        return ResponseEntity.status(HttpStatus.CREATED).body(CollaboratorResponse.from(saved));
    }

    @GetMapping
    public ResponseEntity<List<CollaboratorResponse>> findAll() {
        List<CollaboratorResponse> collaborators =  listCollaboratorUseCase.listAll().stream().map(CollaboratorResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(collaborators);
    }

    @GetMapping("/{id}")

    public ResponseEntity<CollaboratorResponse> getCollaborator(@PathVariable UUID id) {
        Collaborator collaborator = getCollaboratorUseCase.getById(id);
        return  ResponseEntity.ok(CollaboratorResponse.from(collaborator));
    }

}
