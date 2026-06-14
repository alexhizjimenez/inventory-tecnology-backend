package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}

    

