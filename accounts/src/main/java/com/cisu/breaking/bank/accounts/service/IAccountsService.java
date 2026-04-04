package com.cisu.breaking.bank.accounts.service;

import com.cisu.breaking.bank.accounts.dto.CustomerDto;
import com.cisu.breaking.bank.accounts.entity.Customer;

public interface IAccountsService {


    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);


    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDto fetchAccount(String mobileNumber);

}
