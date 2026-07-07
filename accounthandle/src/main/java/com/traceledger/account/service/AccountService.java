package com.traceledger.account.service;

import com.traceledger.account.model.Account;
import com.traceledger.account.model.Transaction;
import com.traceledger.account.repository.AccountRepository;
import com.traceledger.account.repository.TransactionRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository,
                          TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    // Create Account
    public Account createAccount(String accountId) {

        if (accountRepository.existsById(accountId)) {
            return accountRepository.findById(accountId).orElseThrow();
        }

        Account account = new Account();
        account.setAccountId(accountId);
        account.setBalance(0.0);

        return accountRepository.save(account);
    }

    // Apply Transaction
    public Account applyTransaction(Transaction tx) {

        Account account = accountRepository.findById(tx.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Idempotency check
        if (transactionRepository.existsById(tx.getEventId())) {
            return account;
        }

        if ("CREDIT".equalsIgnoreCase(tx.getType())) {
            account.setBalance(account.getBalance() + tx.getAmount());
        } else if ("DEBIT".equalsIgnoreCase(tx.getType())) {
            account.setBalance(account.getBalance() - tx.getAmount());
        } else {
            throw new RuntimeException("Invalid Transaction Type");
        }

        accountRepository.save(account);
        transactionRepository.save(tx);

        return account;
    }

    // Get Balance
    public double getBalance(String accountId) {

        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Get Account Details
    public Account getAccount(String accountId) {

        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}