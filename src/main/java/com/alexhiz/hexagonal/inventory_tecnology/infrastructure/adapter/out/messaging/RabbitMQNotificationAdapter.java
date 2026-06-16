package com.alexhiz.hexagonal.inventory_tecnology.infrastructure.adapter.out.messaging;

import com.alexhiz.hexagonal.inventory_tecnology.application.port.out.NotificationPort;
import com.alexhiz.hexagonal.inventory_tecnology.infrastructure.config.RabbitMQConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RabbitMQNotificationAdapter implements NotificationPort {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendLowStockNotification(String message) {
        rabbitTemplate.convertAndSend(
            RabbitMQConfig.EXCHANGE,
            RabbitMQConfig.ROUTING_KEY,
            message
        );
    }
}
