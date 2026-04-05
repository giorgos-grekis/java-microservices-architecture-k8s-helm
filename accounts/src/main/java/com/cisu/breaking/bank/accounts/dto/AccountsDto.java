package com.cisu.breaking.bank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDto {
    @Schema(
            description = "Account Number",
            example = "123456789"
    )
    @NotBlank(message = "Account Number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type",
            example = "Saving"
    )
    @NotBlank(message = "Account Type is required")
    private String accountType;

    @Schema(
            description = "Branch Address",
            example = "10 Downing St, London, SW1A 2AA"
    )
    @NotBlank(message = "Branch Address is required")
    private String branchAddress;
}
