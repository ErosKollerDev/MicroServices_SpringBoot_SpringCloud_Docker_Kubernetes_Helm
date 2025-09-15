package com.eazybytes.loans.dto;

import lombok.Data;

@Data
public class LoansDto {

    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private Integer totalLoan;
    private Integer amountPaid;
    private Integer outstandingAmount;

}
