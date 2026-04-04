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
       if (mobileNumber != null && mobileNumber.startsWith(" ")) {
           mobileNumber = mobileNumber.replaceFirst(" ", "+");
       }

       mobileNumber = mobileNumber.trim();
       CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
       return ResponseEntity.status(HttpStatus.OK).body(customerDto);
   }
}
