package bank.management.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO for PIN change requests
 */
@Data
public class PinChangeRequest {

    @NotBlank(message = "Current PIN is required")
    private String currentPin;

    @NotBlank(message = "New PIN is required")
    private String newPin;

    @NotBlank(message = "Confirm PIN is required")
    private String confirmPin;
}
