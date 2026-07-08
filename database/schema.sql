CREATE TABLE account (
    account_id VARCHAR(255) PRIMARY KEY,
    balance DOUBLE NOT NULL
);

CREATE TABLE event (
    event_id VARCHAR(255) PRIMARY KEY,
    account_id VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    currency VARCHAR(255) NOT NULL,
    event_timestamp DATETIME NOT NULL
);

CREATE TABLE transaction (
    event_id VARCHAR(255) PRIMARY KEY,
    account_id VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    timestamp DATETIME NOT NULL
);