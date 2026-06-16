package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.persistence.products;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductPersistenceMapper {
    public Product toDomain(ProductEntity entity) {
        if (entity == null) return null;
        return Product.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .sku(entity.getSku())
                .stock(entity.getStock())
                .minimumStock(entity.getMinimumStock())
                .category(entity.getCategory())
                .createdAt(entity.getCreatedAt())
                .status(entity.getStatus())
                .version(entity.getVersion())
                .build();
    }

    public ProductEntity toEntity(Product product) {
        if (product == null) return null;
        return ProductEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .sku(product.getSku())
                .stock(product.getStock())
                .minimumStock(product.getMinimumStock())
                .category(product.getCategory())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .version(product.getVersion())
                .build();
    }
}
