package com.traceledger.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.traceledger.account.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}
