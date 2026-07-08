# Event Ledger Microservices Assessment

A production-ready Spring Boot microservices application that demonstrates event-driven financial transaction processing using REST APIs, Service Discovery, API Gateway, Observability, and Resiliency.

---

# Architecture

```
                   +----------------------+
                   |      API Gateway     |
                   |      Port : 8085     |
                   +----------+-----------+
                              |
               -------------------------------
               |                             |
               |                             |
       +-------v-------+             +-------v--------+
       | Event Ledger  |             | Account Service|
       | Port : 8080   |             | Port : 8081    |
       +-------+-------+             +-------+--------+
               \                             /
                \                           /
                 \                         /
                  +-----------------------+
                  | Eureka Server (8761) |
                  +-----------------------+
```

---

# Microservices

## 1. API Gateway

Responsibilities

- Single entry point
- Request Routing
- Service Discovery Integration

Runs on

```
http://localhost:8085
```

---

## 2. Event Ledger Service

Responsibilities

- Accept financial events
- Validate requests
- Persist events
- Forward transactions to Account Service
- Implements Circuit Breaker

Runs on

```
http://localhost:8080
```

---

## 3. Account Service

Responsibilities

- Create Accounts
- Maintain Balance
- Process Credit/Debit Transactions
- Store Transaction History

Runs on

```
http://localhost:8081
```

---

## 4. Eureka Service Registry

Responsibilities

- Service Registration
- Service Discovery

Runs on

```
http://localhost:8761
```

---

# Technology Stack

- Java 17
- Spring Boot 3.x
- Spring Cloud Gateway
- Spring Cloud Netflix Eureka
- Spring Data JPA
- Spring Boot Actuator
- Resilience4j
- MySQL
- Maven
- REST APIs

---

# Project Structure

```
event-ledger-microservices-assessment
│
├── api-gateway
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
├── README.md
│
└── .gitignore
```

---

# Features

- Event Driven Processing
- Account Management
- API Gateway
- Eureka Service Discovery
- REST Communication
- Global Exception Handling
- Request Validation
- Idempotency
- Structured Logging
- Trace ID Propagation
- Spring Boot Actuator
- Custom Metrics
- Health Monitoring
- Circuit Breaker
- Retry Configuration
- Production-ready Configuration

---

# Database Setup

Create the databases.

```sql
CREATE DATABASE event_ledger_gateway;

CREATE DATABASE account_service;
```

Run

```
database/schema.sql
```

Update database credentials inside

```
eventLedger/src/main/resources/application.properties

accounthandle/src/main/resources/application.properties
```

---

# Start Services

Start services in the following order.

### 1. Eureka Server

```
service-register
```

Port

```
8761
```

---

### 2. Account Service

```
accounthandle
```

Port

```
8081
```

---

### 3. Event Ledger

```
eventLedger
```

Port

```
8080
```

---

### 4. API Gateway

```
api-gateway
```

Port

```
8085
```

---

# REST APIs

## Create Account

```
POST /accounts/{accountId}
```

Example

```
POST http://localhost:8085/accounts/ACC1001
```

---

## Get Account Balance

```
GET /accounts/{accountId}/balance
```

Example

```
GET http://localhost:8085/accounts/ACC1001/balance
```

---

## Create Event

```
POST /events
```

Example

```
POST http://localhost:8085/events
```

Request

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

# Observability

Spring Boot Actuator endpoints

```
/actuator/health

/actuator/info

/actuator/metrics
```

Additional Features

- Structured Logging
- Trace ID Propagation
- Health Monitoring
- Application Metrics

---

# Resiliency

Implemented using Resilience4j.

- Circuit Breaker
- Retry
- Fallback Handling

---

# Production Ready Features

- API Gateway
- Service Discovery
- Centralized Routing
- Bean Validation
- Global Exception Handling
- Structured Logging
- Idempotency
- Configuration Externalization
- REST Communication

---

# Build

For each microservice

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
- Docker
- Kubernetes
- OpenTelemetry
- Prometheus
- Grafana
- ELK Stack
- Distributed Tracing

---

# GitHub Repository

https://github.com/Pradhumnkhot/event-ledger-microservices-assessment

---

# Author

**Pradyumna Dilip Khot**

Email: pradyumnakhot@gmail.com

Phone: +91 7058861258

GitHub

https://github.com/Pradhumnkhot
