package com.bank.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime date;
    private double amount;
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Transaction() {}

    public Transaction(Account account, double amount, double balance) {
        this.account = account;
        this.amount = amount;
        this.balance = balance;
        this.date = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public LocalDateTime getDate() { return date; }
    public double getAmount() { return amount; }
    public double getBalance() { return balance; }
    public Account getAccount() { return account; }
} 