package bank.management.system.repository;

import bank.management.system.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Account entity operations
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Optional<Account> findByAccountNumber(String accountNumber);
    
    Optional<Account> findByAccountNumberAndPin(String accountNumber, String pin);
    
    List<Account> findByFormNo(String formNo);
    
    boolean existsByAccountNumber(String accountNumber);
    
    @Query("SELECT a FROM Account a WHERE a.accountNumber = ?1 AND a.pin = ?2")
    Optional<Account> validateAccount(String accountNumber, String pin);
}
