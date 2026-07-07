package com.traceledger.gateway.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
public class Event {

    @Id
    @NotBlank(message = "Event ID is required")
    private String eventId;

    @NotBlank(message = "Account ID is required")
    private String accountId;

    @NotBlank(message = "Transaction type is required")
    private String type;

    @Positive(message = "Amount must be greater than zero")
    private double amount;

    @NotBlank(message = "Currency is required")
    private String currency;

    @NotNull(message = "Event timestamp is required")
    private LocalDateTime eventTimestamp;

    public Event() {
    }

    public Event(String eventId, String accountId, String type,
                 double amount, String currency, LocalDateTime eventTimestamp) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.currency = currency;
        this.eventTimestamp = eventTimestamp;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public LocalDateTime getEventTimestamp() {
        return eventTimestamp;
    }

    public void setEventTimestamp(LocalDateTime eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventId='" + eventId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", eventTimestamp=" + eventTimestamp +
                '}';
    }
}