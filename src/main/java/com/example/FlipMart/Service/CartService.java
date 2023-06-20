package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.FlipMart.Dto.RequestDto.ItemRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CartResponseDto;
import com.example.FlipMart.Dto.ResponseDto.OrderResponseDto;
import com.example.FlipMart.Model.Item;
import com.example.FlipMart.exception.CustomerNotFoundException;
import com.example.FlipMart.exception.EmptyCartException;
import com.example.FlipMart.exception.InsufficientQuantityException;
import com.example.FlipMart.exception.InvalidCardException;

public interface CartService {
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;
}
