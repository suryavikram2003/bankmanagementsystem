package bank.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class for Bank Management System
 * This replaces the individual main() methods in the Swing application
 */
@SpringBootApplication
public class BankManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankManagementSystemApplication.class, args);
    }
}
