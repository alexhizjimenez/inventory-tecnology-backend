package com.alexhiz.hexagonal.inventory_tecnology.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {
    @EqualsAndHashCode.Include
    private UUID id;
    private String name;
    private String description;
    private String sku;
    private Integer stock;
    private StatusProduct status;
    private CategoryProduct category;
    private LocalDateTime createdAt;
    private Long version;

}
