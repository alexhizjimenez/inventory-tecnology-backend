package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.assignments;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusAssignment;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.collaborators.CollaboratorEntity;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "assignments")
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collaborator_id", nullable = false)
    private CollaboratorEntity collaborator;

    @Column(name = "delivery_date", nullable = false, updatable = false)
    private LocalDateTime deliveryDate;

    @Column(name = "returned_date", nullable = true)
    private LocalDateTime returnedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAssignment status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.deliveryDate = LocalDateTime.now();
        if( this.status == null ) { this.status = StatusAssignment.ACTIVE; }
    }
}
