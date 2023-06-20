package com.example.FlipMart.Dto.ResponseDto;


import com.example.FlipMart.Model.Item;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String customerName;

    String orderNo;

    int totalValue;

    String cardUsed;

    Date orderDate;

    List<ItemResponseDto> items;
}
