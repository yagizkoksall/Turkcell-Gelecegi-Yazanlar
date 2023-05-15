package com.example.inventoryservice.business.kafka.consumer;

import com.example.commonpackage.events.inventory.CarCreatedEvent;
import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import com.example.commonpackage.events.inventory.RentalDeletedEvent;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.inventoryservice.business.abstracts.CarService;
import com.example.inventoryservice.entities.enums.State;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final CarService service;

    @KafkaListener(
            topics = "rental-created",
            groupId = "inventory-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        // changer car state
        service.changeStateByCarId(State.RENTED, event.getCarId());
        log.info("Rental created event consumed {}", event);
    }
    @KafkaListener(
            topics = "rental-deleted",
            groupId = "inventory-rental-delete"
    )
    public void consume(RentalDeletedEvent event) {
        // changer car state
        service.changeStateByCarId(State.AVAILABLE, event.getCarId());
        log.info("Rental deleted event consumed {}", event);
    }
}

