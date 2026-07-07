package com.traceledger.gateway.service;

import java.time.LocalDateTime;

import org.slf4j.MDC;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.traceledger.gateway.model.Event;
import com.traceledger.gateway.model.Transaction;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class EventProducerService {

    private final RestTemplate restTemplate;

    public EventProducerService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "accountService", fallbackMethod = "fallback")
    public void publishEvent(Event event) {

        Transaction tx = new Transaction();
        tx.setEventId(event.getEventId());
        tx.setAccountId(event.getAccountId());
        tx.setAmount(event.getAmount());
        tx.setTimestamp(LocalDateTime.now());

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Trace-Id", MDC.get("traceId"));

        HttpEntity<Transaction> request = new HttpEntity<>(tx, headers);

        restTemplate.postForEntity(
                "http://localhost:8081/accounts/transaction",
                request,
                Object.class
        );
    }

    public void fallback(Event event, Exception ex) {
        throw new RuntimeException("Account Service unavailable. Please try again later.");
    }
}