package com.example.FlipMart.Controller;

import com.example.FlipMart.Enum.CardType;

import com.example.FlipMart.Dto.RequestDto.CardRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CardResponseDto;
import com.example.FlipMart.Service.CardService;
import com.example.FlipMart.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // tell me the card type which exists max number of times.
    @GetMapping("/maximum-cards-of-a-cardtype")
    public ResponseEntity maximumCardsOfACardType(){

        String cardType = cardService.maximumCardsOfACardType();
        return new ResponseEntity(cardType, HttpStatus.FOUND);
    }

    // tell me the card type which exists min number of times.
    @GetMapping("/minimum-cards-of-a-cardtype")
    public ResponseEntity minimumCardsOfACardType(){

        String cardType = cardService.minimumCardsOfACardType();
        return new ResponseEntity(cardType, HttpStatus.FOUND);
    }
}
