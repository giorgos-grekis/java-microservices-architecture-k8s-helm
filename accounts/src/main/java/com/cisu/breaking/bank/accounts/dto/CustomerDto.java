package com.cisu.breaking.bank.accounts.dto;

import com.cisu.breaking.bank.accounts.validation.PhoneNumber;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDto {
    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @PhoneNumber
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;
}
