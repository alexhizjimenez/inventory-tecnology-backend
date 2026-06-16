package com.alexhiz.hexagonal.inventory_tecnology.application.port.out;

import com.alexhiz.hexagonal.inventory_tecnology.domain.event.ProductLowStockReached;

public interface ProductEventPublisherPort {
    void publishLowStockEvent(ProductLowStockReached event);
}
