package com.traceledger.gateway.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.traceledger.gateway.model.Event;
import com.traceledger.gateway.service.EventService;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) {

        Event savedEvent = eventService.saveEvent(event);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {

        Event event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEventsByAccount(
            @RequestParam String accountId) {

        return ResponseEntity.ok(eventService.getEventsByAccount(accountId));
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Event Gateway is running!");
    }
}