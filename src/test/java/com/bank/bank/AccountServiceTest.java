package com.bank;

import com.bank.model.*;
import com.bank.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.bank.repository.*;

class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;
    @InjectMocks
    private AccountService accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        account = new Account();
    }

    @Test
    void deposit_shouldIncreaseBalanceAndAddTransaction() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.deposit(1L, 100.0);
        assertEquals(100.0, account.getBalance());
        assertEquals(1, account.getTransactions().size());
        assertEquals(100.0, account.getTransactions().get(0).getAmount());
    }

    @Test
    void withdraw_shouldDecreaseBalanceAndAddTransaction() {
        account.deposit(200.0);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        accountService.withdraw(1L, 50.0);
        assertEquals(150.0, account.getBalance());
        assertEquals(2, account.getTransactions().size());
        assertEquals(-50.0, account.getTransactions().get(1).getAmount());
    }

    @Test
    void deposit_shouldThrowExceptionForNegativeAmount() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertThrows(IllegalArgumentException.class, () -> accountService.deposit(1L, -10.0));
    }

    @Test
    void withdraw_shouldThrowExceptionForInsufficientFunds() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertThrows(IllegalArgumentException.class, () -> accountService.withdraw(1L, 50.0));
    }

    @Test
    void getStatement_shouldReturnTransactions() {
        account.deposit(100.0);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        assertEquals(1, accountService.getStatement(1L).size());
    }

    @Test
    void deposit_shouldThrowExceptionForMissingAccount() {
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> accountService.deposit(2L, 10.0));
    }
} 