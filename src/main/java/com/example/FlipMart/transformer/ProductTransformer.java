package com.example.FlipMart.transformer;

import com.example.FlipMart.Dto.RequestDto.ProductRequestDto;
import com.example.FlipMart.Dto.ResponseDto.ProductResponseDto;
import com.example.FlipMart.Enum.ProductStatus;
import com.example.FlipMart.Model.Product;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .productStatus(product.getProductStatus())
                .build();
    }
}
