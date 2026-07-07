package com.traceledger.gateway.service;

import com.traceledger.gateway.model.Event;
import com.traceledger.gateway.repository.EventRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final EventProducerService eventProducerService;

    public EventService(EventRepository eventRepository, EventProducerService eventProducerService) {
        this.eventRepository = eventRepository;
        this.eventProducerService = eventProducerService;
    }

    public Event saveEvent(Event event) {
        // Idempotency check
        if (eventRepository.existsById(event.getEventId())) {
            return eventRepository.findById(event.getEventId()).orElse(event);
        }

        Event savedEvent = eventRepository.save(event);

        // Publish to Kafka after saving
        eventProducerService.publishEvent(savedEvent);

        return savedEvent;
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id).orElse(null);
    }

    public List<Event> getEventsByAccount(String accountId) {
        return eventRepository.findByAccountId(accountId);
    }
}
