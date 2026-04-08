package com.cisu.breaking.bank.cards.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

/**
 Not registered via @EnableConfigurationProperties,
 marked as Spring component, or scanned via @ConfigurationPropertiesScan
 */
@ConfigurationProperties(prefix = "cards")
public record CardsContactInfoDto(String message,
                                  Map<String, String> contactDetails,
                                  List<String> onCallSupport) {
}
