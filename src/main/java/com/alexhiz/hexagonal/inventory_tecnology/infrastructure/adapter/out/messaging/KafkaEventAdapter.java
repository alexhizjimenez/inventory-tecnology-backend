package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.messaging;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.ProductEventPublisherPort;
import com.alexhiz.hexagonal.inventory_tecnology.domain.event.ProductLowStockReached;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaEventAdapter implements ProductEventPublisherPort {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void publishLowStockEvent(ProductLowStockReached event) {
        kafkaTemplate.send("product-low-stock", event.productId().toString(), event);
    }
}
