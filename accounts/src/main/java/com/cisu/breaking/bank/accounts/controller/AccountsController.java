package com.cisu.breaking.bank.accounts.controller;

import com.cisu.breaking.bank.accounts.constans.AccountsConstants;
import com.cisu.breaking.bank.accounts.dto.CustomerDto;
import com.cisu.breaking.bank.accounts.dto.ResponseDto;
import com.cisu.breaking.bank.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Accounts",
        description = "CRUD REST APIs for Accounts "
)

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

   private IAccountsService iAccountsService;

   @Operation(
           summary = "Create Account",
           description = "API to create a new customer and account"
   )
   @ApiResponse(
           responseCode = "201",
           description = "HTTP Status CREATED"
   )
   @PostMapping("/create")
   public ResponseEntity<ResponseDto> createAccount(
           @Valid
           @RequestBody CustomerDto customerDto
   ) {
       iAccountsService.createAccount(customerDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(
                        AccountsConstants.STATUS_201,
                        AccountsConstants.MESSAGE_201));
   }

    @Operation(
            summary = "Get Account Details",
            description = "fetch account details with mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
   @GetMapping("/fetch")
   public ResponseEntity<CustomerDto> fetchAccountDetails(
           @Valid @RequestParam String mobileNumber
   ) {
       String formattedNumber = formatMobileNumber(mobileNumber);
       CustomerDto customerDto = iAccountsService.fetchAccount(formattedNumber);
       return ResponseEntity.status(HttpStatus.OK).body(customerDto);
   }

    @Operation(
            summary = "Update Account Details",
            description = "update account and customer based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }


    @Operation(
            summary = "Delete Account & Customer Details",
            description = "delete account and customer based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(
//            @PhoneNumber
            @RequestParam String mobileNumber) {
        String formattedNumber = formatMobileNumber(mobileNumber);

        boolean isDeleted = iAccountsService.deleteAccount(formattedNumber);
        if(isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }



    private String formatMobileNumber(String mobileNumber) {
        if (mobileNumber != null && mobileNumber.startsWith(" ")) {
            mobileNumber = mobileNumber.replaceFirst(" ", "+");
        }
        return mobileNumber != null ? mobileNumber.trim() : null;
    }
}
