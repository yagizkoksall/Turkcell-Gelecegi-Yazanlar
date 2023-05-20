package com.example.inventoryservice.business.kafka.consumer;

import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import com.example.commonpackage.events.inventory.RentalDeletedEvent;
import com.example.inventoryservice.business.abstracts.CarService;
import com.example.inventoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MaintenanceConsumer {
    private final CarService service;

    @KafkaListener(
            topics = "maintenance-created",
            groupId = "inventory-maintenance-create"
    )
    public void consume(RentalCreatedEvent event) {
        // changer car state
        service.changeStateByCarId(State.MAINTENANCE, event.getCarId());
        log.info("Maintenance created event consumed {}", event);
    }
    @KafkaListener(
            topics = "maintenance-deleted",
            groupId = "inventory-maintenance-delete"
    )
    public void consume(RentalDeletedEvent event) {
        // changer car state
        service.changeStateByCarId(State.AVAILABLE, event.getCarId());
        log.info("Maintenance deleted event consumed {}", event);
    }

}
