package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account Number is mandatory")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Please provide valid account number")
    private Long accountNumber;
    @NotEmpty(message = "Account Type is mandatory")
    private String accountType;
    @NotEmpty(message = "Branch Address is mandatory")
    private String branchAddress;
}
