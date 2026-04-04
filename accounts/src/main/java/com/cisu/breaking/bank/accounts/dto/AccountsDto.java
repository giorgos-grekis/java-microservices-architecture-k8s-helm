package com.cisu.breaking.bank.accounts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @NotBlank(message = "Account Number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account Number must be 10 digits")
    private Long accountNumber;

    @NotBlank(message = "Account Type is required")
    private String accountType;

    @NotBlank(message = "Branch Address is required")
    private String branchAddress;
}
