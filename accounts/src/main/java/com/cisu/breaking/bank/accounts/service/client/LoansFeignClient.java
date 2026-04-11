package com.cisu.breaking.bank.accounts.service.client;

import com.cisu.breaking.bank.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Help accounts ms to connect with loans ms
 */
@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/v1/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestParam String mobileNumber);

}
