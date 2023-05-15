package com.example.filterservice.business.kafka.consumer;


import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import com.example.commonpackage.events.inventory.RentalDeletedEvent;
import com.example.filterservice.business.abstracts.FilterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final FilterService service;

    @KafkaListener(
            topics = "rental-created",
            groupId = "filter-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        var filter = service.getByCarId(event.getCarId());
        filter.setState("RENTED");
        service.add(filter);
        log.info("Rental created event consumed {}", event);
    }
    @KafkaListener(
            topics = "rental-deleted",
            groupId = "filter-rental-delete"
    )
    public void consume(RentalDeletedEvent event) {
        var filter = service.getByCarId(event.getCarId());
        filter.setState("AVAILABLE");
        service.add(filter);
        log.info("Rental deleted event consumed {}", event);
    }
}
