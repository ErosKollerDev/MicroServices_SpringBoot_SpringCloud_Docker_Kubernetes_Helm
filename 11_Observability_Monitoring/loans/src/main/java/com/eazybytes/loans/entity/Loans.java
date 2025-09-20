package com.eazybytes.loans.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Loans extends BaseEntity {

//    `loan_id` int NOT NULL AUTO_INCREMENT,
//  `mobile_number` varchar(15) NOT NULL,
//  `loan_number` varchar(100) NOT NULL,
//  `loan_type` varchar(100) NOT NULL,
//  `total_loan` int NOT NULL,
//            `amount_paid` int NOT NULL,
//            `outstanding_amount` int NOT NULL,
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;

    private String mobileNumber;
    private String loanNumber;
    private String loanType;
    private Integer totalLoan;
    private Integer amountPaid;
    private Integer outstandingAmount;

}
