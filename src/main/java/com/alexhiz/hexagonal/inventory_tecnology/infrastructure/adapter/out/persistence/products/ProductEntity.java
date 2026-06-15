package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products;

import java.time.LocalDateTime;
import java.util.UUID;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.CategoryProduct;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusProduct;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(unique = true, nullable = false)
    private String sku;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusProduct status;

    @Column(nullable = false)
    @Min(0)
    private Integer stock;

    @Column(nullable = false)
    private CategoryProduct category;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Version
    private Long version;


    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null ) this.status = StatusProduct.ACTIVE;
        if (this.stock == null ) this.stock = 0;
    }

}
