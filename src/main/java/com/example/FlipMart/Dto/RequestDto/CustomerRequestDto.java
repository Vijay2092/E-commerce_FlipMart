package com.example.FlipMart.Dto.RequestDto;

import com.example.FlipMart.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;

    String emailId;

    String mobNo;

    Gender gender;
}
