package bank.management.system.service;

import bank.management.system.dto.TransactionRequest;
import bank.management.system.entity.Account;
import bank.management.system.entity.Transaction;
import bank.management.system.repository.AccountRepository;
import bank.management.system.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for transaction operations
 */
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    /**
     * Perform deposit transaction
     */
    @Transactional
    public Transaction deposit(String accountNumber, String pin, Double amount) {
        // Get account
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setPin(pin);
        transaction.setTransactionType("Deposit");
        transaction.setAmount(amount);

        // Update account balance
        Double currentBalance = calculateBalance(accountNumber);
        account.setBalance(currentBalance + amount);
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    /**
     * Perform withdrawal transaction
     */
    @Transactional
    public Transaction withdraw(String accountNumber, String pin, Double amount) {
        // Get account
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // Check balance
        Double currentBalance = calculateBalance(accountNumber);
        if (currentBalance < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        // Create transaction
        Transaction transaction = new Transaction();
        transaction.setAccountNumber(accountNumber);
        transaction.setPin(pin);
        transaction.setTransactionType("Withdrawal");
        transaction.setAmount(amount);

        // Update account balance
        account.setBalance(currentBalance - amount);
        accountRepository.save(account);

        return transactionRepository.save(transaction);
    }

    /**
     * Get balance for an account
     */
    public Double getBalance(String accountNumber) {
        return calculateBalance(accountNumber);
    }

    /**
     * Calculate balance from transactions
     */
    private Double calculateBalance(String accountNumber) {
        Double balance = transactionRepository.calculateBalance(accountNumber);
        return balance != null ? balance : 0.0;
    }

    /**
     * Get transaction history
     */
    public List<Transaction> getTransactionHistory(String accountNumber) {
        return transactionRepository.findByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }

    /**
     * Get mini statement (last 10 transactions)
     */
    public List<Transaction> getMiniStatement(String accountNumber) {
        return transactionRepository.findTop10ByAccountNumberOrderByTransactionDateDesc(accountNumber);
    }
}
