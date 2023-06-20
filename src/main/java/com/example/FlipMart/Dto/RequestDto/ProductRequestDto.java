package com.example.FlipMart.Dto.RequestDto;


import com.example.FlipMart.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String sellerEmailId;

    String name;

    Integer price;

    Category category;

    Integer quantity;
}
