package com.traceledger.gateway.model;

import java.time.LocalDateTime;

public class Transaction {

    private String eventId;

    private String accountId;

    private double amount;

    private LocalDateTime timestamp;

    public Transaction() {
    }

    public Transaction(String eventId, String accountId, double amount, LocalDateTime timestamp) {
        this.eventId = eventId;
        this.accountId = accountId;
        this.amount = amount;
        this.timestamp = timestamp;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}