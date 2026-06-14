package com.alexhiz.hexagonal.inventory_tecnology.application.services;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product.CreateProductUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product.GetProductUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product.ListProductUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.ProductRepositoryPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.ProductInvalidStockException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.ProductNotFoundException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.exception.ProductWithOutStockException;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService implements CreateProductUseCase, GetProductUseCase, ListProductUseCase {

    private final ProductRepositoryPort productRepositoryPort;

    @Override
    public Product create(Product product) {
        if(product.getStock() <= 0) {
            throw new ProductInvalidStockException("Product stock invalid" + product.getSku());
        }
        return productRepositoryPort.save(product);
    }

    @Override
    public Product getById(UUID id) {
        return productRepositoryPort.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found:" + id));
    }

    @Override
    public List<Product> listAll() {
        return productRepositoryPort.findAll();
    }
}
