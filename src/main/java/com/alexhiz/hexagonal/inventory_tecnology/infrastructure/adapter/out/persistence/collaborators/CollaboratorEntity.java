package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.collaborators;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.AreaCollaborator;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.PositionCollaborator;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "collaborators")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollaboratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 255, nullable = false)
    private String fullName;

    @Column(nullable = false, length = 6)
    private String code;

    @Enumerated(EnumType.STRING)
    @Column(length = 255, nullable = false)
    private AreaCollaborator area;

    @Enumerated(EnumType.STRING)
    @Column(length = 255, nullable = false)
    private PositionCollaborator position;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
