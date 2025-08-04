package com.example.backend.kafka;

import com.example.backend.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaOrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Value("${app.kafka.topic}")
    private String topic;

    @Autowired
    public KafkaOrderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendOrder(Order order){
        try{
            String json = objectMapper.writeValueAsString(order);
            kafkaTemplate.send(topic, json);
            System.out.println("Sent order to Kafka: " + json);
        } catch (Exception e){
            log.error("Log Error : {}", e.getMessage());
        }
    }
}
