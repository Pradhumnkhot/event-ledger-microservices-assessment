package com.traceledger.gateway.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Event {
    @Id
    private String accountId;
    private double balance;

    public Event() {}
    public Event(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    // Getters and setters
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
