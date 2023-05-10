package com.example.inventoryservice.kafka.producer;


import com.example.commonpackage.events.inventory.BrandDeletedEvent;
import com.example.commonpackage.events.inventory.CarCreatedEvent;
import com.example.commonpackage.events.inventory.CarDeletedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class InventoryProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendMessage(CarCreatedEvent event) {
        Message<CarCreatedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, "car-created")
                .build();
        log.info("Car created event consumed {}", event);

        kafkaTemplate.send(message);
    }

    public void sendMessage(CarDeletedEvent event){
        Message<CarDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"car-deleted")
                .build();
        log.info("Car deleted event consumed {}", event);

        kafkaTemplate.send(message);
    }

    public void sendMessage(BrandDeletedEvent event){
        Message<BrandDeletedEvent> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC,"brand-deleted")
                .build();
        log.info("Brand deleted event consumed {}",event);
        kafkaTemplate.send(message);
    }
}
