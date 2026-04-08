package com.cisu.breaking.bank.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 Not registered via @EnableConfigurationProperties,
 marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfoDto(String message,
                                     Map<String, String> contactDetails,
                                     List<String> onCallSupport) {
}
