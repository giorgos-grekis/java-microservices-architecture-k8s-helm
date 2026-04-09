package com.cisu.breaking.bank.accounts.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 Not registered via @EnableConfigurationProperties,
 marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
@ConfigurationProperties(prefix = "accounts")
@Getter
@Setter
public class AccountsContactInfoDto {

    private String message;
    private Map<String, String> contactDetails;
    private  List<String> onCallSupport;
}
