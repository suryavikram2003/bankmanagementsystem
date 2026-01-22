package bank.management.system.repository;

import bank.management.system.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Transaction entity operations
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccountNumberOrderByTransactionDateDesc(String accountNumber);
    
    List<Transaction> findTop10ByAccountNumberOrderByTransactionDateDesc(String accountNumber);
    
    @Query("SELECT SUM(CASE WHEN t.transactionType = 'Deposit' THEN t.amount ELSE -t.amount END) " +
           "FROM Transaction t WHERE t.accountNumber = ?1")
    Double calculateBalance(String accountNumber);
}
