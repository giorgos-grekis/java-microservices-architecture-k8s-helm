package com.cisu.breaking.bank.accounts.dto;

import com.cisu.breaking.bank.accounts.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {
    @NotBlank(message = "Name is required")
    @Size(min=5, max=30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @PhoneNumber
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    private AccountsDto accountsDto;
}
