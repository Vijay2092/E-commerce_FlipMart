package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.ItemRequestDto;
import com.example.FlipMart.Model.Item;
import com.example.FlipMart.exception.CustomerNotFoundException;
import com.example.FlipMart.exception.InsufficientQuantityException;
import com.example.FlipMart.exception.OutOfStockException;
import com.example.FlipMart.exception.ProductNotFoundException;

public interface ItemService {
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}

