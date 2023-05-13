package com.example.rentalservice.business.kafka.producer;

import com.example.commonpackage.events.inventory.CarCreatedEvent;
import com.example.commonpackage.events.inventory.RentalCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(RentalCreatedEvent event) {
        Message<RentalCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "rental-created")
                .build();
        log.info("rental created event consumed {}", event);

        kafkaTemplate.send(message);
    }
}
