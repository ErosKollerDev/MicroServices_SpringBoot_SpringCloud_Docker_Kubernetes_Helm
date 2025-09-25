package com.eazybytes.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Customer",
        description = "Schema to hold Customer information")
public class CustomerDto {

    @Schema(description = "Customer Id", example = "123456")
    private Long id;

    @Schema(description = "Customer Name", example = "")
    @NotEmpty(message = "Name is mandatory")
    @Size(min = 4, max = 90, message = "Name should be between 4 and 90 characters")
    private String name;

    @Schema(description = "Customer Email", example = "johndoe@gmail.com")
    @NotEmpty(message = "Email is mandatory")
    @Email(message = "Please provide valid email")
    private String email;

    @Schema(description = "Customer Mobile Number", example = "11962861010")
    @NotEmpty(message = "Mobile Number is mandatory")
    @Pattern(regexp = "(^$|[0-9]{11})", message = "Please provide valid mobile number")
    private String mobileNumber;

    private AccountsDto accountsDto;

}
