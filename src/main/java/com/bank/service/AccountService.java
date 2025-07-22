package com.bank.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import java.util.List;

@Service
public class AccountService {


    private final AccountRepository accountRepository;

    private final TransactionRepository transactionRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public Account deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.deposit(amount);
        accountRepository.save(account);
        return account;
    }

    @Transactional
    public Account withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.withdraw(amount);
        accountRepository.save(account);
        return account;
    }

    public List<Transaction> getStatement(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getTransactions();
    }

    public AccountRepository getAccountRepository() {
        return accountRepository;
    }
} 