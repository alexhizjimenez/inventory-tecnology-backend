package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.ProductRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements ProductRepositoryPort {
    private final ProductRepository  productRepository;
    private final ProductPersistenceMapper mapper;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = mapper.toEntity(product);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id).map(mapper::toDomain);
    }
}
