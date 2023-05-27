package com.example.invoiceservice.business.kafka.consumer;

import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import com.example.commonpackage.utils.mappers.ModelMapperService;
import com.example.invoiceservice.business.abstracts.InvoiceService;
import com.example.invoiceservice.business.dto.requests.create.CreateInvoiceRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class RentalConsumer {
    private final InvoiceService service;
    private final ModelMapperService mapper;

    @KafkaListener(
            topics = "invoice-created",
            groupId = "invoice-rental-create"
    )
    public void consume(RentalCreatedEvent event) {
        // changer car state
       var request = mapper.forRequest().map(event, CreateInvoiceRequest.class);
       service.add(request);

        log.info("Rental created event consumed {}", event);
    }
}
