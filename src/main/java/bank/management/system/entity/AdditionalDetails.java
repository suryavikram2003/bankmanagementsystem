package bank.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing additional user details from signuptwo table
 */
@Entity
@Table(name = "signuptwo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalDetails {

    @Id
    @Column(name = "formno", length = 20)
    private String formNo;

    @Column(name = "religion", length = 50)
    private String religion;

    @Column(name = "category", length = 50)
    private String category;

    @Column(name = "income", length = 50)
    private String income;

    @Column(name = "education", length = 50)
    private String education;

    @Column(name = "occupation", length = 50)
    private String occupation;

    @Column(name = "pan", length = 20)
    private String panNumber;

    @Column(name = "aadhar", length = 20)
    private String aadharNumber;

    @Column(name = "scitizen", length = 10)
    private String seniorCitizen;

    @Column(name = "eaccount", length = 10)
    private String existingAccount;

    @OneToOne
    @MapsId
    @JoinColumn(name = "formno")
    private User user;
}
