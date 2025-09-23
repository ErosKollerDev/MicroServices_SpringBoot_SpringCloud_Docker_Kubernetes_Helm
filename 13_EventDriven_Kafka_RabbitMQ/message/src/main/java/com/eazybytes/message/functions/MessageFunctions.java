package com.eazybytes.message.functions;

import com.eazybytes.message.dto.AccountsMsgDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;
import java.util.function.Function;


@Slf4j
@Configuration
public class MessageFunctions {

    @Bean
    public Function<AccountsMsgDto, AccountsMsgDto> email() {
        return (accountsMsgDto) -> {
            log.info("Processing Email Message ...\n Sending email ... \n Email details : {}", accountsMsgDto);
            return accountsMsgDto;
        };

    }

    @Bean
    public Function<AccountsMsgDto, Long> sms() {
        return (sms) -> {
            log.info("Processing SMS Message ...\n Sending SMS ... \n SMS details :{}", sms);
            return sms.accountNumber();
        };
    }

    @Bean
    public Function<AccountsMsgDto, String> msg() {
        return (msg) -> {
            log.info("Message received : {}", msg);
            return "Processing MSG Message ...\n Sending MSG ... \n MSG details :{}".formatted(msg.mobileNumber());
        };
    }
}