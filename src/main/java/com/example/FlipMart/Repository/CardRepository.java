package com.example.FlipMart.Repository;

import com.example.FlipMart.Enum.CardType;
import com.example.FlipMart.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    Card findByCardNo(String cardNo);

    @Query(value = "SELECT COUNT(card_type) FROM card WHERE card_type = :cardType", nativeQuery = true)
    int cardsOfACardType(CardType cardType);
}
