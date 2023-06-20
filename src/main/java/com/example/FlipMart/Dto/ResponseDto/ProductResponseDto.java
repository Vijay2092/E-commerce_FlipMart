package com.example.FlipMart.Dto.ResponseDto;


import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String productName;

    String sellerName;

    Category category;

    int price;

    ProductStatus productStatus;
}
