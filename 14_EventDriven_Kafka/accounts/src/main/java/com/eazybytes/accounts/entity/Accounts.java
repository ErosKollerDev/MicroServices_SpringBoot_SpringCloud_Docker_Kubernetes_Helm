package com.eazybytes.accounts.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Table(name = "ACCOUNTS")
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {

    @Id
    private Long accountNumber;

    private Long customerId;

    private String accountType;
    private String branchAddress;
    private Boolean communicationSw;


}
