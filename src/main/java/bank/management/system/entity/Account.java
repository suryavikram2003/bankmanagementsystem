package bank.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity representing account details combining signupthree and login tables
 */
@Entity
@Table(name = "login")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    @Id
    @Column(name = "accountnumber", length = 25)
    private String accountNumber;

    @Column(name = "formno", length = 20)
    private String formNo;

    @Column(name = "pin", length = 10, nullable = false)
    private String pin;

    @Column(name = "account_type", length = 50)
    private String accountType;

    @Column(name = "services", length = 255)
    private String services;

    @Column(name = "balance")
    private Double balance = 0.0;

    @OneToOne
    @JoinColumn(name = "formno", insertable = false, updatable = false)
    private User user;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions;
}
