package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestParam double amount) {
        Account account = accountService.deposit(id, amount);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestParam double amount) {
        Account account = accountService.withdraw(id, amount);
        return ResponseEntity.ok(account);
    }

    @GetMapping("/{id}/statement")
    public ResponseEntity<List<Transaction>> getStatement(@PathVariable Long id) {
        List<Transaction> statement = accountService.getStatement(id);
        return ResponseEntity.ok(statement);
    }


    @PostMapping("")
    public ResponseEntity<Account> createAccount(@RequestParam(defaultValue = "0.0") double initialBalance) {
        Account account = new Account();
        if (initialBalance > 0) {
            account.deposit(initialBalance);
        }
        account = accountService.getAccountRepository().save(account);
        return ResponseEntity.ok(account);
    }
} 