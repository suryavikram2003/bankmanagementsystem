package bank.management.system.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO for transaction requests (deposit, withdrawal)
 */
@Data
public class TransactionRequest {

    private String accountNumber;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    private String transactionType; // Deposit or Withdrawal
}
