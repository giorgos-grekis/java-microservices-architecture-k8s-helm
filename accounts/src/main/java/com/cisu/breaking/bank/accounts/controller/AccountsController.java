package com.cisu.breaking.bank.accounts.controller;

import com.cisu.breaking.bank.accounts.constans.AccountsConstants;
import com.cisu.breaking.bank.accounts.dto.CustomerDto;
import com.cisu.breaking.bank.accounts.dto.ResponseDto;
import com.cisu.breaking.bank.accounts.service.IAccountsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

   private IAccountsService iAccountsService;

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

   @GetMapping("/fetch")
   public ResponseEntity<CustomerDto> fetchAccountDetails(
           @Valid @RequestParam String mobileNumber
   ) {
       String formattedNumber = formatMobileNumber(mobileNumber);
       CustomerDto customerDto = iAccountsService.fetchAccount(formattedNumber);
       return ResponseEntity.status(HttpStatus.OK).body(customerDto);
   }

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
