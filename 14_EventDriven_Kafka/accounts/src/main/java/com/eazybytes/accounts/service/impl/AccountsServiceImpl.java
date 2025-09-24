package com.eazybytes.accounts.service.impl;


import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.AccountsMsgDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final StreamBridge streamBridge;

    @Override
    public void createAccount(CustomerDto customerDto) {
        if (this.customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isEmpty()) {
            Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
//            customer.setCreatedBy("Eros");
            Customer savedCustomer = this.customerRepository.save(customer);
            Accounts newAccount = createNewAccount(savedCustomer);
//            newAccount.setCreatedBy("Eros");
            Accounts savedAccount = this.accountsRepository.save(newAccount);
            this.sendCommunication(savedAccount, savedCustomer);
        } else {
            throw new CustomerAlreadyExistsException("Customer already exists with mobile number: " + customerDto.getMobileNumber());
        }
    }

    private void sendCommunication(Accounts savedAccount, Customer savedCustomer) {
        AccountsMsgDto accountsMsgDto =
                new AccountsMsgDto(savedAccount.getAccountNumber(), savedCustomer.getName(), savedCustomer.getEmail(), savedCustomer.getMobileNumber());
        log.info("Details of the Account created :\n {}", accountsMsgDto);
        log.info("Sending message to \nsendCommunication-out-0 topic");
        boolean send = this.streamBridge.send("sendCommunication-out-0", accountsMsgDto);
        log.info("Message produced with success to message-out-0 topic : \n{}", send);
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> {
            return new ResourceNotFoundException("Customer not found ", "mobile number: ", mobileNumber);
        });
        Accounts accounts = this.accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> {
            return new ResourceNotFoundException("Account not found ", "customer id", customer.getCustomerId().toString());
        });
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber()).orElseThrow(() -> {
                return new ResourceNotFoundException("Customer not found ", "mobile number: ", customerDto.getMobileNumber());
            });
            Accounts accounts = this.accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(() -> {
                return new ResourceNotFoundException("Account", "account number", accountsDto.getAccountNumber().toString());
            });
            CustomerMapper.mapToCustomer(customerDto, customer);
            AccountsMapper.mapToAccounts(accountsDto, accounts);
            customerRepository.save(customer);
            accountsRepository.save(accounts);
            isUpdated = true;
        }
        return isUpdated;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = this.customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found ", "mobile number: ", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        this.customerRepository.deleteById(customer.getCustomerId());
        return true;
    }

    @Override
    public boolean updateCommunicationStatus(Long accountNumber) {
        Accounts accounts = this.accountsRepository.findById(accountNumber).orElseThrow(() -> {
            return new ResourceNotFoundException("Account", "account number", accountNumber.toString());
        });
        accounts.setCommunicationSw(true);
        this.accountsRepository.save(accounts);
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + (long) (Math.random() * ((9999999999L - 1000000000L) + 1));
        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVINGS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }
}
