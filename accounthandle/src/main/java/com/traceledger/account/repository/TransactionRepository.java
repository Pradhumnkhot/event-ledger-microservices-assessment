package com.traceledger.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traceledger.account.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
