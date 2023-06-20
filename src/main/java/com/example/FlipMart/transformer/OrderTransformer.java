package com.example.FlipMart.transformer;

import com.example.FlipMart.Dto.ResponseDto.ItemResponseDto;
import com.example.FlipMart.Dto.ResponseDto.OrderResponseDto;
import com.example.FlipMart.Model.Customer;
import com.example.FlipMart.Model.Item;
import com.example.FlipMart.Model.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity OrderRequestDtoToOrder(Item item, Customer customer){

        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .customerName(orderEntity.getCustomer().getName())
                .totalValue(orderEntity.getTotalValue())
                .orderNo(orderEntity.getOrderNo())
                .items(itemResponseDtoList)
                .build();
    }
}