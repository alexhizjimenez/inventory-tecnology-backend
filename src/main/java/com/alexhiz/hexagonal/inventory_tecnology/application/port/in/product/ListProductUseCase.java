package com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;

import java.util.List;

public interface ListProductUseCase {
    List<Product> listAll();
}
