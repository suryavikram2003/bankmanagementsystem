package bank.management.system.service;

import bank.management.system.entity.Account;
import bank.management.system.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service for account management operations
 */
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Validate account credentials
     */
    public Optional<Account> validateAccount(String accountNumber, String pin) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent() && passwordEncoder.matches(pin, account.get().getPin())) {
            return account;
        }
        return Optional.empty();
    }

    /**
     * Get account by account number
     */
    public Optional<Account> getAccount(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    /**
     * Get all accounts (for admin)
     */
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    /**
     * Change PIN
     */
    @Transactional
    public boolean changePin(String accountNumber, String currentPin, String newPin) {
        Optional<Account> accountOpt = validateAccount(accountNumber, currentPin);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setPin(passwordEncoder.encode(newPin));
            accountRepository.save(account);
            return true;
        }
        return false;
    }

    /**
     * Delete account (for admin)
     */
    @Transactional
    public boolean deleteAccount(String accountNumber) {
        if (accountRepository.existsByAccountNumber(accountNumber)) {
            accountRepository.deleteById(accountNumber);
            return true;
        }
        return false;
    }

    /**
     * Update account balance
     */
    @Transactional
    public void updateBalance(String accountNumber, Double balance) {
        Optional<Account> accountOpt = accountRepository.findByAccountNumber(accountNumber);
        if (accountOpt.isPresent()) {
            Account account = accountOpt.get();
            account.setBalance(balance);
            accountRepository.save(account);
        }
    }
}
