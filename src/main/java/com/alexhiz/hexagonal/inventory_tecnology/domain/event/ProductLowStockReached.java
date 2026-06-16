package com.alexhiz.hexagonal.inventory_tecnology.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductLowStockReached(
        UUID productId,
        String productName,
        Integer currentStock,
        Integer threshold,
        LocalDateTime occurredAt
) {
}