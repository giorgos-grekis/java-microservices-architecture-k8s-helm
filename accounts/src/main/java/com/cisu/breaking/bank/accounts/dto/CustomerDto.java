package com.cisu.breaking.bank.accounts.dto;

import com.cisu.breaking.bank.accounts.validation.PhoneNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
@Data
public class CustomerDto {

    @Schema(
            description = "Name of the customer",
            example = "John Doe"
    )
    @NotBlank(message = "Name is required")
    @Size(min=5, max=30, message = "The length of the customer name should be between 5 and 30")
    private String name;

    @Schema(
            description = "Email address of the customer",
            example = "johndoe@example.com"
    )
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(
            description = "Mobile Number of the customer",
            example = "+306949999999"
    )
    @PhoneNumber
    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

    @Schema(
            description = "Accounts details of the Customer"
    )
    private AccountsDto accountsDto;
}
