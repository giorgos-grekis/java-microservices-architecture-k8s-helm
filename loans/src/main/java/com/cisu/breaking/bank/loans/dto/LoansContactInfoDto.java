package com.cisu.breaking.bank.loans.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 Not registered via @EnableConfigurationProperties,
 marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
@ConfigurationProperties(prefix = "loans")
public record LoansContactInfoDto(String message,
                                  Map<String, String> contactDetails,
                                  List<String> onCallSupport) {
}
