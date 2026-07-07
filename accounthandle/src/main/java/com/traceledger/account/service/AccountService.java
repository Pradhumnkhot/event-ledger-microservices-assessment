package com.traceledger.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.traceledger.account.model.Account;
import com.traceledger.account.model.Transaction;
import com.traceledger.account.repository.AccountRepository;
import com.traceledger.account.repository.TransactionRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(String accountId) {
        Account account = new Account();
        account.setAccountId(accountId);
        account.setBalance(0.0);
        return accountRepository.save(account);
    }

    public Account applyTransaction(Transaction tx) {
        Account account = accountRepository.findById(tx.getAccountId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + tx.getAmount());
        accountRepository.save(account);

        // Save transaction record
        transactionRepository.save(tx);

        return account;
    }

    public double getBalance(String accountId) {
        return accountRepository.findById(accountId)
                .map(Account::getBalance)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
