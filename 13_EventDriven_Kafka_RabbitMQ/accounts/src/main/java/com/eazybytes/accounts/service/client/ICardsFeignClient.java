package com.eazybytes.accounts.service.client;


import com.eazybytes.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cards", fallback = CardsFallback.class)//, url = "http://localhost:8081"
public interface ICardsFeignClient {

//    @PostMapping("/api/create")
//    public ResponseEntity<ResponseDto> createCard(String mobileNumber);

    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader(name = "eazybank-correlation-id") String correlationId, @RequestParam String mobileNumber);


}




