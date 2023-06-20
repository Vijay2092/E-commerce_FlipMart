package com.example.FlipMart.Service.impl;

import com.example.FlipMart.Dto.RequestDto.CardRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CardResponseDto;
import com.example.FlipMart.Enum.CardType;
import com.example.FlipMart.Model.Card;
import com.example.FlipMart.Model.Customer;
import com.example.FlipMart.Repository.CardRepository;
import com.example.FlipMart.Repository.CustomerRepository;
import com.example.FlipMart.Service.CardService;
import com.example.FlipMart.exception.CustomerNotFoundException;
import com.example.FlipMart.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Invalid Email ID!!!");
        }

        // dto -> entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);

        // add the card to customer's card list
        customer.getCards().add(card);
        // save the card and customer
        Customer savedCustomer = customerRepository.save(customer);

        // preparing the response Dto
        return CardTransformer.CardToCardResponseDto(card);
    }

    @Override
    public String maximumCardsOfACardType() {

//        int visaCard = cardRepository.cardsOfACardType(CardType.VISA);
//        int masterCard = cardRepository.cardsOfACardType(CardType.MASTERCARD);
//        int rupayCard = cardRepository.cardsOfACardType(CardType.RUPAY);
//
//        if(visaCard >= masterCard && visaCard >= rupayCard){
//            return "VISA";
//        }
//        else if(masterCard >= visaCard && masterCard >= rupayCard){
//            return "MASTERCARD";
//        }
//        else{
//            return "RUPAY";
//        }

        List<Card> cards = cardRepository.findAll();

        int visaCard = 0;
        int masterCard = 0;
        int rupayCard = 0;

        for(Card card : cards){
            if(card.getCardType() == CardType.VISA){
                visaCard++;
            }
            else if (card.getCardType() == CardType.MASTERCARD) {
                masterCard++;
            }
            else {
                rupayCard++;
            }
        }

        if(visaCard >= masterCard && visaCard >= rupayCard){
            return "VISA";
        }
        else if(masterCard >= visaCard && masterCard >= rupayCard){
            return "MASTERCARD";
        }
        else{
            return "RUPAY";
        }
    }

    @Override
    public String minimumCardsOfACardType() {

        List<Card> cards = cardRepository.findAll();

        int visaCard = 0;
        int masterCard = 0;
        int rupayCard = 0;

        for(Card card : cards){
            if(card.getCardType() == CardType.VISA){
                visaCard++;
            }
            else if (card.getCardType() == CardType.MASTERCARD) {
                masterCard++;
            }
            else {
                rupayCard++;
            }
        }

        if(visaCard <= masterCard && visaCard <= rupayCard){
            return "VISA";
        }
        else if(masterCard <= visaCard && masterCard <= rupayCard){
            return "MASTERCARD";
        }
        else{
            return "RUPAY";
        }
    }
}
