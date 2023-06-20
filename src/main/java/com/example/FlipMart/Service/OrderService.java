package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.OrderRequestDto;
import com.example.FlipMart.Dto.ResponseDto.OrderResponseDto;
import com.example.FlipMart.Model.Card;
import com.example.FlipMart.Model.Cart;
import com.example.FlipMart.Model.OrderEntity;
import com.example.FlipMart.exception.CustomerNotFoundException;
import com.example.FlipMart.exception.InsufficientQuantityException;
import com.example.FlipMart.exception.InvalidCardException;
import com.example.FlipMart.exception.ProductNotFoundException;

import java.util.List;

public interface OrderService {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException;

    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException;

    public List<OrderResponseDto> top5OrderWithHighestValue();

    public List<OrderResponseDto> allOrdersOfACustomer(String emailId) throws CustomerNotFoundException;

    public List<OrderResponseDto> top5HighestValueOrdersOfACustomer(String emailId) throws CustomerNotFoundException;

    public List<OrderResponseDto> top5RecentOrdersOfACustomer(String emailId) throws CustomerNotFoundException;
}

