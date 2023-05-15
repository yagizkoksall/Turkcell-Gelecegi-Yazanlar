package com.example.commonpackage.configuration.kafka.producer;

import com.example.commonpackage.utils.kafka.producer.KafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class KafkaProducerConfig {
    @Bean
    KafkaProducer getKafkaProducer(KafkaTemplate<String,Object> template){
        return new KafkaProducer(template);
    }
}
