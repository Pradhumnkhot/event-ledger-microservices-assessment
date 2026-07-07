package com.traceledger.gateway.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.traceledger.gateway.model.Event;

@Service
public class EventProducerService {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    public EventProducerService(KafkaTemplate<String, Event> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishEvent(Event event) {
        kafkaTemplate.send("event-topic", event.getEventId(), event);
    }
}
