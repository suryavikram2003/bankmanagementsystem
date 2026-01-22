package bank.management.system.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for user signup - Page 1 (Personal Details)
 */
@Data
public class SignupRequest {

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Father's name is required")
    private String fatherName;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Email(message = "Valid email is required")
    @NotBlank(message = "Email is required")
    private String email;

    private String maritalStatus;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Pincode is required")
    private String pincode;

    @NotBlank(message = "State is required")
    private String state;

    // Page 2 fields
    private String religion;
    private String category;
    private String income;
    private String education;
    private String occupation;
    private String panNumber;
    private String aadharNumber;
    private String seniorCitizen;
    private String existingAccount;

    // Page 3 fields
    private String accountType;
    private String atmCard;
    private String internetBanking;
    private String mobileBanking;
    private String emailAlerts;
    private String chequeBook;
    private String eStatement;
}
