package com.alexhiz.hexagonal.inventory_tecnology.application.services;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.CreateAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.GetAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.assignment.ListAssignmentUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.AssignmentRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.CollaboratorRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.ProductRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.AssignmentNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.CollaboratorNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.ProductNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Assignment;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Collaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssignmentService implements CreateAssignmentUseCase, GetAssignmentUseCase, ListAssignmentUseCase {

    private final AssignmentRepositoryPort assignmentRepositoryPort;
    private final ProductRepositoryPort productRepositoryPort;
    private final CollaboratorRepositoryPort collaboratorRepositoryPort;

    @Override
    public Assignment create(Assignment assignment) {
        Product product = productRepositoryPort.findById(assignment.getProduct().getId())
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id: " + assignment.getProduct().getId()));
        Collaborator collaborator = collaboratorRepositoryPort.findById(assignment.getCollaborator().getId())
                .orElseThrow(() -> new CollaboratorNotFoundException("Collaborator not found with id: " + assignment.getCollaborator().getId()));

        assignment.setProduct(product);
        assignment.setCollaborator(collaborator);

        return assignmentRepositoryPort.save(assignment);
    }

    @Override
    public Assignment getById(UUID id) {
        return assignmentRepositoryPort.findById(id).orElseThrow(() -> new AssignmentNotFoundException("No se encontro ninguna asignacion " + id));
    }

    @Override
    public List<Assignment> listAll() {
        return assignmentRepositoryPort.findAll();
    }
}
