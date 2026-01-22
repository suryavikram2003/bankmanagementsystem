package bank.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity representing transaction history from bank table
 */
@Entity
@Table(name = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accountnumber", length = 25, nullable = false)
    private String accountNumber;

    @Column(name = "pin", length = 10)
    private String pin;

    @Column(name = "date")
    private LocalDateTime transactionDate;

    @Column(name = "type", length = 20)
    private String transactionType; // Deposit, Withdrawal

    @Column(name = "amount")
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountnumber", insertable = false, updatable = false)
    private Account account;

    @PrePersist
    protected void onCreate() {
        transactionDate = LocalDateTime.now();
    }
}
