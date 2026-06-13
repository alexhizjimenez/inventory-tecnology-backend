package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;

import java.util.UUID;

public interface GetProductUseCase {
    Product getById(UUID id);
}
