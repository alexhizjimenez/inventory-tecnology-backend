package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;

public interface CreateProductUseCase {
    Product create(Product product);
}
