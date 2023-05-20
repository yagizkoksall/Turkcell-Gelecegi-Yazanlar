package com.example.filterservice.business.kafka.consumer;

import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import com.example.commonpackage.events.inventory.RentalDeletedEvent;
import com.example.filterservice.business.abstracts.FilterService;
import com.example.filterservice.repository.FilterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MaintenanceConsumer {
    private final FilterService service;

    @KafkaListener(
            topics = "maintenance-created",
            groupId = "filter-maintenance-create"
    )
    public void consume(RentalCreatedEvent event) {
        // changer car state
        var filter = service.getByCarId(event.getCarId());
        filter.setState("MAINTENANCE");
        service.add(filter);
        log.info("Maintenance created event consumed {}", event);
    }
    @KafkaListener(
            topics = "maintenance-deleted",
            groupId = "filter-maintenance-delete"
    )
    public void consume(RentalDeletedEvent event) {
        // changer car state
        var filter = service.getByCarId(event.getCarId());
        filter.setState("AVAILABLE");
        service.add(filter);
        log.info("Maintenance deleted event consumed {}", event);
    }

}
    