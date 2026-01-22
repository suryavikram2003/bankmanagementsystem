package bank.management.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for user login requests
 */
@Data
public class LoginRequest {

    @NotBlank(message = "Account number is required")
    private String accountNumber;

    @NotBlank(message = "PIN is required")
    private String pin;
}
