package com.alexhiz.hexagonal.inventory_tecnology.application.port.out;

public interface NotificationPort {
    void sendLowStockNotification(String message);
}
