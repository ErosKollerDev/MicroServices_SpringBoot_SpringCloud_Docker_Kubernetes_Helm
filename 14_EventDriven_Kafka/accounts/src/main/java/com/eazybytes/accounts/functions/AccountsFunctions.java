package com.eazybytes.accounts.functions;

import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Slf4j
@Configuration
@AllArgsConstructor
public class AccountsFunctions {


    private final IAccountsService accountsService;

    @Bean
    public Consumer<Long> updateAccountCommunicationStatus() {
        return (accountNumber) -> {
            this.accountsService.updateCommunicationStatus(accountNumber);
            log.info("\nUpdated\nAccountNumber from Queue : {}\nEmail and SMS send it with success .....\n####################################################", accountNumber);
        };
    }

}
