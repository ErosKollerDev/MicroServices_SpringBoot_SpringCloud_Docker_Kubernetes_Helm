package com.eazybytes.cards.service;


import com.eazybytes.cards.repository.CardsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CardsService {


    private final CardsRepository cardsRepository;

}
