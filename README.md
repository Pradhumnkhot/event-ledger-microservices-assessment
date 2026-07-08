# Event Ledger Microservices Assessment

A production-ready Spring Boot microservices application that demonstrates event-driven financial transaction processing with service discovery, observability, resiliency, and REST-based communication.

---

# Project Overview

This project consists of three Spring Boot microservices:

## 1. Event Ledger Service

Responsible for:

- Receiving financial transaction events
- Validating incoming requests
- Persisting event data
- Forwarding transactions to the Account Service
- Implementing observability and resiliency

Runs on:

```
http://localhost:8080
```

---

## 2. Account Service

Responsible for:

- Creating customer accounts
- Maintaining account balances
- Processing credit/debit transactions
- Storing transaction history

Runs on:

```
http://localhost:8081
```

---

## 3. Eureka Service Registry

Responsible for:

- Service Discovery
- Service Registration
- Health Monitoring

Runs on:

```
http://localhost:8761
```

---

# Technology Stack

- Java 17
- Spring Boot 3.x
- Spring Data JPA
- Spring Web
- MySQL
- Spring Cloud Netflix Eureka
- Spring Boot Actuator
- Resilience4j Circuit Breaker
- Maven
- REST APIs

---

# Project Structure

```
event-ledger-microservices-assessment
│
├── eventLedger
│
├── accounthandle
│
├── service-register
│
├── database
│   └── schema.sql
│
└── README.md
```

---

# Features

- Event Processing
- Account Management
- REST-based Microservice Communication
- Service Discovery using Eureka
- Spring Boot Actuator
- Health Monitoring
- Application Metrics
- Structured Logging
- Global Exception Handling
- Request Validation
- Idempotent Event Processing
- Resilience4j Circuit Breaker
- Production-ready Configuration

---

# Database Setup

Create the databases:

```sql
CREATE DATABASE event_ledger_gateway;

CREATE DATABASE account_service;
```

Import the schema:

```
database/schema.sql
```

Update MySQL credentials inside:

```
eventLedger/src/main/resources/application.properties

accounthandle/src/main/resources/application.properties
```

---

# Service Startup Order

Start the applications in the following order:

### 1. Eureka Service Registry

```
service-register
```

URL

```
http://localhost:8761
```

---

### 2. Account Service

```
accounthandle
```

URL

```
http://localhost:8081
```

---

### 3. Event Ledger Service

```
eventLedger
```

URL

```
http://localhost:8080
```

---

# REST APIs

## Account Service

### Create Account

```
POST /accounts/{accountId}
```

Example

```
POST http://localhost:8081/accounts/ACC1001
```

---

### Get Account Balance

```
GET /accounts/{accountId}/balance
```

Example

```
GET http://localhost:8081/accounts/ACC1001/balance
```

---

### Apply Transaction

```
POST /accounts/transaction
```

Example Request

```json
{
  "eventId": "EVT001",
  "accountId": "ACC1001",
  "type": "CREDIT",
  "amount": 500,
  "timestamp": "2026-07-08T10:30:00"
}
```

---

## Event Ledger Service

### Create Event

```
POST /events
```

Example

```
POST http://localhost:8080/events
```

Request Body

```json
{
  "eventId": "EVT001",
  "accountId": "ACC1001",
  "type": "CREDIT",
  "amount": 500,
  "currency": "INR",
  "eventTimestamp": "2026-07-08T10:30:00"
}
```

---

### Get Event by ID

```
GET /events/{eventId}
```

---

### Get Events by Account

```
GET /events/account/{accountId}
```

---

# Observability

Spring Boot Actuator is enabled.

Available endpoints:

```
/actuator/health

/actuator/info

/actuator/metrics
```

Additional Observability Features:

- Structured Logging
- Trace ID Filter
- Health Monitoring
- Custom Metrics
- Request Tracking

---

# Resiliency

Implemented using Resilience4j.

Features:

- Circuit Breaker
- Fallback Handling
- Retry Configuration
- Graceful Error Handling

---

# Production-ready Features

- Global Exception Handling
- Bean Validation
- Service Discovery
- Health Checks
- Structured Logging
- Circuit Breaker
- REST Communication
- Idempotency Check
- Configuration Externalization

---

# Build

For each microservice:

```bash
mvn clean install
```

---

# Run

```bash
mvn spring-boot:run
```

---

# Future Enhancements

- Kafka Integration
- Docker Support
- Kubernetes Deployment
- OpenTelemetry
- Distributed Tracing
- Prometheus & Grafana Monitoring
- API Gateway
- Centralized Logging (ELK)

---

# Author

**Pradyumna Dilip Khot**

GitHub Repository:

https://github.com/Pradhumnkhot/event-ledger-microservices-assessment
