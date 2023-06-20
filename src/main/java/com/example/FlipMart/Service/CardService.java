package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.CardRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CardResponseDto;
import com.example.FlipMart.exception.CustomerNotFoundException;

public interface CardService {
    public CardResponseDto addCard (CardRequestDto cardRequestDto)throws CustomerNotFoundException;
    public String maximumCardsOfACardType();

    public String minimumCardsOfACardType();

    }
