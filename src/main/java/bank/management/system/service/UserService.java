package bank.management.system.service;

import bank.management.system.dto.SignupRequest;
import bank.management.system.entity.Account;
import bank.management.system.entity.AdditionalDetails;
import bank.management.system.entity.User;
import bank.management.system.repository.AccountRepository;
import bank.management.system.repository.AdditionalDetailsRepository;
import bank.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Service for user management operations
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AdditionalDetailsRepository additionalDetailsRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Register a new user with all details
     */
    @Transactional
    public Account registerUser(SignupRequest request) {
        // Generate form number
        String formNo = generateFormNumber();

        // Create User entity
        User user = new User();
        user.setFormNo(formNo);
        user.setName(request.getName());
        user.setFatherName(request.getFatherName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setGender(request.getGender());
        user.setEmail(request.getEmail());
        user.setMaritalStatus(request.getMaritalStatus());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setPincode(request.getPincode());
        user.setState(request.getState());
        userRepository.save(user);

        // Create AdditionalDetails entity
        AdditionalDetails additionalDetails = new AdditionalDetails();
        additionalDetails.setFormNo(formNo);
        additionalDetails.setReligion(request.getReligion());
        additionalDetails.setCategory(request.getCategory());
        additionalDetails.setIncome(request.getIncome());
        additionalDetails.setEducation(request.getEducation());
        additionalDetails.setOccupation(request.getOccupation());
        additionalDetails.setPanNumber(request.getPanNumber());
        additionalDetails.setAadharNumber(request.getAadharNumber());
        additionalDetails.setSeniorCitizen(request.getSeniorCitizen());
        additionalDetails.setExistingAccount(request.getExistingAccount());
        additionalDetails.setUser(user);
        additionalDetailsRepository.save(additionalDetails);

        // Create Account entity
        String accountNumber = generateAccountNumber();
        String pin = generatePin();

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setFormNo(formNo);
        account.setPin(passwordEncoder.encode(pin)); // Hash the PIN
        account.setAccountType(request.getAccountType());
        account.setServices(buildServicesString(request));
        account.setBalance(0.0);
        account.setUser(user);

        accountRepository.save(account);

        // Set the plain PIN temporarily for display to user (will be shown once)
        Account result = new Account();
        result.setAccountNumber(accountNumber);
        result.setPin(pin); // Plain text for one-time display
        result.setFormNo(formNo);
        result.setAccountType(request.getAccountType());

        return result;
    }

    /**
     * Generate a random form number
     */
    private String generateFormNumber() {
        Random random = new Random();
        long number = Math.abs((random.nextLong() % 9000L) + 1000L);
        return String.valueOf(number);
    }

    /**
     * Generate a random account number
     */
    private String generateAccountNumber() {
        Random random = new Random();
        long number = Math.abs((random.nextLong() % 900000000L) + 100000000L);
        return String.valueOf(number);
    }

    /**
     * Generate a random 4-digit PIN
     */
    private String generatePin() {
        Random random = new Random();
        long number = Math.abs((random.nextLong() % 9000L) + 1000L);
        return String.valueOf(number);
    }

    /**
     * Build services string from checkboxes
     */
    private String buildServicesString(SignupRequest request) {
        StringBuilder services = new StringBuilder();
        if ("on".equals(request.getAtmCard())) services.append("ATM CARD ");
        if ("on".equals(request.getInternetBanking())) services.append("Internet Banking ");
        if ("on".equals(request.getMobileBanking())) services.append("Mobile Banking ");
        if ("on".equals(request.getEmailAlerts())) services.append("EMAIL Alerts ");
        if ("on".equals(request.getChequeBook())) services.append("Cheque Book ");
        if ("on".equals(request.getEStatement())) services.append("E-Statement ");
        return services.toString().trim();
    }
}
