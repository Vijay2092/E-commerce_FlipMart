package com.example.FlipMart.transformer;

import com.example.FlipMart.Dto.ResponseDto.ItemResponseDto;
import com.example.FlipMart.Model.Item;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(int quantity){

        return Item.builder()
                .requiredQuantity(quantity)
                .build();
    }

    public static ItemResponseDto ItemToItemResponseDto(Item item) {

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
