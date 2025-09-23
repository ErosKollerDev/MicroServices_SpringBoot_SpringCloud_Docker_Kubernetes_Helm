package com.eazybytes.accounts.controller;


import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.service.impl.CustomerServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Tag(
        name = "REST for CustomerDetails",
        description = "REST APIs in to fetch customer details"
)
@RestController
@RequestMapping(value = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
@Validated
public class CustomerController {


    private final CustomerServiceImpl customerServiceImpl;

    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader(name = "eazybank-correlation-id") String correlationId,
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{11})", message = "Please provide valid mobile number")
            String mobileNumber) {
        log.info("Correlation Id: {}", correlationId);
//        log.debug("fetchCustomerDetails method start");
        CustomerDetailsDto customerDetailsDto = this.customerServiceImpl.fetchCustomerDetails(mobileNumber, correlationId);
//        log.debug("fetchCustomerDetails method end");
        return ResponseEntity.ok(customerDetailsDto);
    }

}
