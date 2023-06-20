package com.example.FlipMart.Dto.ResponseDto;

import com.example.FlipMart.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {
    String customerName;

    String cardNo;

    CardType cardType;
}
