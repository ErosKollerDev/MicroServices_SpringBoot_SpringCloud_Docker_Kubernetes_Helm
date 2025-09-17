package com.eazybytes.accounts.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactInfDto(String message,
                                    Map<String, String> contactDetails,
                                    List<String> onCallSupport) {
}
