package com.cisu.breaking.bank.accounts.service.client;

import com.cisu.breaking.bank.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Help accounts ms to connect with cards ms
 */
@FeignClient(name = "cards", fallback = CardFeignClient.class)
public interface CardFeignClient {

    @GetMapping(value = "/api/v1/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(
            @RequestHeader("breaking-bank-correlation-id") String correlationId,
            @RequestParam String mobileNumber);

}

