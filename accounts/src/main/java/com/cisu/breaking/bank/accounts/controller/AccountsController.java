package com.cisu.breaking.bank.accounts.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountsController {

    @GetMapping("/")
    public String sayHello() {
        return "Hi World";
    }
}
