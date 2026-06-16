package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product.CreateProductUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product.GetProductUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.application.port.in.product.ListProductUseCase;
import com.alexhiz.hexagonal.inventory_tecnology.domain.model.Product;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.ProductRequest;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.in.rest.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final ListProductUseCase listProductUseCase;
    private final GetProductUseCase getProductUseCase;

    @Operation(
            summary = "Crea producto",
            description = "Retorna la información de un producto creado"
    )
    @ApiResponse(responseCode = "200", description = "Producto creado")
    @ApiResponse(responseCode = "404", description = "Error al crear producto")
    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .sku(request.getSku())
                .minimumStock(request.getMinimumStock())
                .stock(request.getStock())
                .build();
        Product saved = createProductUseCase.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponse.from(saved));
    }

    @Operation(
            summary = "Lista de productos",
            description = "Retorna la lista de productos"
    )
    @ApiResponse(responseCode = "200", description = "Todos los productos")
    @ApiResponse(responseCode = "404", description = "Error al obtener productos")
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = listProductUseCase.listAll().stream().map(ProductResponse::from).collect(Collectors.toList());
        return ResponseEntity.ok(products);
    }


    @Operation(
            summary = "Obtener producto por ID",
            description = "Retorna la información de un producto"
    )
    @ApiResponse(responseCode = "200", description = "Producto encontrado")
    @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable UUID id) {
        Product product = getProductUseCase.getById(id);
        return ResponseEntity.ok(ProductResponse.from(product));
    }
    
}
