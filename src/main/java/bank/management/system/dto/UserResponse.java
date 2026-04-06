package bank.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for user/account response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private String formNo;
    private String name;
    private String accountNumber;
    private String email;
    private Double balance;
    private String accountType;
}
