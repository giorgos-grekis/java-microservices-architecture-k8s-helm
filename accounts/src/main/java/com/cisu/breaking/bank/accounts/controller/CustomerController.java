package com.cisu.breaking.bank.accounts.controller;

import com.cisu.breaking.bank.accounts.dto.CustomerDetailsDto;
import com.cisu.breaking.bank.accounts.dto.ErrorResponseDto;
import com.cisu.breaking.bank.accounts.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Customers",
        description = "CRUD REST APIs for Customers details"
)

@RestController
@RequestMapping(path = "/api/v1", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerService iCustomerService;

//    public CustomerController(ICustomerService iCustomerService) {
//        this.iCustomerService = iCustomerService;
//    }

    @Operation(
            summary = "Fetch Customer Details REST API",
            description = "REST API to fetch customer details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader("breaking-bank-correlation-id") String correlationId,
            @RequestParam
//            @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
            String mobileNumber
    ) {
        logger.debug("breaking-bank-correlation-id found: ",  correlationId);
//        String formattedNumber = formatMobileNumber(mobileNumber);
        var customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
        System.out.println("Επιστράφηκε από το Service: " + customerDetailsDto);
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }


    // TODO: move into commons
//    private String formatMobileNumber(String mobileNumber) {
//        if (mobileNumber != null && mobileNumber.startsWith(" ")) {
//            mobileNumber = mobileNumber.replaceFirst(" ", "+");
//        }
//        return mobileNumber != null ? mobileNumber.trim() : null;
//    }
}
