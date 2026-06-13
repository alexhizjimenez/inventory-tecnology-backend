package com.alexhiz.hexagonal.inventory_tecnology.application.port.out;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort {
    Product save(Product product);
    List<Product> findAll();
    Optional<Product> findById(UUID id);
}
