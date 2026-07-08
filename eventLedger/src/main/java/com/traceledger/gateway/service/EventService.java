package com.traceledger.gateway.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.traceledger.gateway.model.Event;
import com.traceledger.gateway.repository.EventRepository;

import io.micrometer.core.instrument.MeterRegistry;

@Service
public class EventService {

    private static final Logger logger =
            LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;
    private final EventProducerService eventProducerService;
    private final MeterRegistry meterRegistry;

    public EventService(EventRepository eventRepository,
                        EventProducerService eventProducerService,
                        MeterRegistry meterRegistry) {

        this.eventRepository = eventRepository;
        this.eventProducerService = eventProducerService;
        this.meterRegistry = meterRegistry;
    }

    /**
     * Save Event
     */
    public Event saveEvent(Event event) {

        // Idempotency Check
        if (eventRepository.existsById(event.getEventId())) {

            logger.warn("Duplicate Event Received : {}", event.getEventId());

            return eventRepository.findById(event.getEventId())
                    .orElseThrow(() ->
                            new RuntimeException("Event already exists"));
        }

        Event savedEvent = eventRepository.save(event);

        // Custom Metric
        meterRegistry.counter("events.created").increment();

        logger.info("Event {} created successfully for Account {}",
                savedEvent.getEventId(),
                savedEvent.getAccountId());

        // Publish Event to Account Service
        eventProducerService.publishEvent(savedEvent);

        return savedEvent;
    }

    /**
     * Get Event by Id
     */
    public Event getEventById(String eventId) {

        logger.info("Fetching Event {}", eventId);

        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));
    }

    /**
     * Get Events by Account
     */
    public List<Event> getEventsByAccount(String accountId) {

        logger.info("Fetching Events for Account {}", accountId);

        return eventRepository.findByAccountId(accountId);
    }
}