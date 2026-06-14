package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto;

import com.alexhiz.hexagonal.inventory_tecnology.domain.model.CategoryProduct;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.StatusProduct;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private String sku;
    private Integer stock;
    private StatusProduct status;
    private CategoryProduct category;
}
