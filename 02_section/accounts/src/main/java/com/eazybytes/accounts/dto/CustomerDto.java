package com.eazybytes.accounts.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDto {

    @NotEmpty(message = "Name is mandatory")
    @Size(min = 4, max = 90, message = "Name should be between 4 and 90 characters")
    private String name;
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Please provide valid email")
    private String email;
    @NotEmpty(message = "Mobile Number is mandatory")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Please provide valid mobile number")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
