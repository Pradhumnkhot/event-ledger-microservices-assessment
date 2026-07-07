package com.traceledger.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.traceledger.account.model.Account;
import com.traceledger.account.model.Transaction;
import com.traceledger.account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{id}")
    public ResponseEntity<Account> createAccount(@PathVariable String id) {
        return ResponseEntity.ok(accountService.createAccount(id));
    }

    @PostMapping("/transaction")
    public ResponseEntity<Account> applyTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(accountService.applyTransaction(transaction));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<Double> getBalance(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getBalance(id));
    }
}