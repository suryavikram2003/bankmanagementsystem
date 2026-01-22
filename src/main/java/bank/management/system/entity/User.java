package bank.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity representing user personal details from signup table
 */
@Entity
@Table(name = "signup")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "formno", length = 20)
    private String formNo;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "fname", length = 100)
    private String fatherName;

    @Column(name = "dob", length = 50)
    private String dateOfBirth;

    @Column(name = "gender", length = 20)
    private String gender;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "marital", length = 20)
    private String maritalStatus;

    @Column(name = "address", length = 200)
    private String address;

    @Column(name = "city", length = 50)
    private String city;

    @Column(name = "pincode", length = 10)
    private String pincode;

    @Column(name = "state", length = 50)
    private String state;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private AdditionalDetails additionalDetails;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Account account;
}
