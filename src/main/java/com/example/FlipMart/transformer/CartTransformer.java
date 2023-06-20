package com.example.FlipMart.transformer;

import com.example.FlipMart.Dto.ResponseDto.CartResponseDto;
import com.example.FlipMart.Dto.ResponseDto.ItemResponseDto;
import com.example.FlipMart.Model.Cart;
import com.example.FlipMart.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto CartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item : cart.getItems()){
            itemResponseDtos.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}