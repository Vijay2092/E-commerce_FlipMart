package com.example.FlipMart.transformer;

import com.example.FlipMart.Dto.RequestDto.CardRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CardResponseDto;
import com.example.FlipMart.Model.Card;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .cardType(cardRequestDto.getCardType())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card){

        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .customerName(card.getCustomer().getName())
                .build();
    }
}