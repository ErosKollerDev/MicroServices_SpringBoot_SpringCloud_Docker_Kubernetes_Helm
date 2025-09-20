package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.*;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerDetailsService;
import com.eazybytes.accounts.service.client.ICardsFeignClient;
import com.eazybytes.accounts.service.client.ILoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ICustomerDetailsService {


    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    private final ICardsFeignClient iCardsFeignClient;
    private final ILoansFeignClient iLoansFeignClient;

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {

        Customer customer = this.customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> {
            return new ResourceNotFoundException("Customer not found ", "mobile number: ", mobileNumber);
        });
        Accounts accounts = this.accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> {
            return new ResourceNotFoundException("Account not found ", "customer id", customer.getCustomerId().toString());
        });

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = this.iCardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        ResponseEntity<LoansDto> loansDtoResponseEntity = this.iLoansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (cardsDtoResponseEntity != null && cardsDtoResponseEntity.getBody() != null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        if (loansDtoResponseEntity != null && loansDtoResponseEntity.getBody() != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
