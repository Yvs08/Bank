package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bank.model.Transaction;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
} 