package com.cisu.breaking.bank.accounts.service.impl;

import com.cisu.breaking.bank.accounts.dto.AccountsDto;
import com.cisu.breaking.bank.accounts.dto.CardsDto;
import com.cisu.breaking.bank.accounts.dto.CustomerDetailsDto;
import com.cisu.breaking.bank.accounts.dto.LoansDto;
import com.cisu.breaking.bank.accounts.entity.Accounts;
import com.cisu.breaking.bank.accounts.entity.Customer;
import com.cisu.breaking.bank.accounts.exception.ResourceNotFoundException;
import com.cisu.breaking.bank.accounts.mapper.AccountsMapper;
import com.cisu.breaking.bank.accounts.mapper.CustomerMapper;
import com.cisu.breaking.bank.accounts.repository.AccountsRepository;
import com.cisu.breaking.bank.accounts.repository.CustomerRepository;
import com.cisu.breaking.bank.accounts.service.ICustomerService;
import com.cisu.breaking.bank.accounts.service.client.CardsFeignClient;
import com.cisu.breaking.bank.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Customer",
                                "mobileNumber",
                                mobileNumber
                        )
                );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Account",
                                "customerId",
                                customer.getCustomerId().toString()
                        )
                );

        var customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        /**
         * Connect with Eureka server and try to get loans instance details
         * and it will perform some load balancing and invoke the actual microservices
         */
        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }


        /**
         * Connect with Eureka server and try to get cards instance details
         * and it will perform some load balancing and invoke the actual microservices
         */
        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
