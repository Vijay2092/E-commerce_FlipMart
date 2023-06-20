package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.ProductRequestDto;
import com.example.FlipMart.Dto.ResponseDto.ProductResponseDto;
import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.exception.ProductNotFoundException;
import com.example.FlipMart.exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);

    public void changeCategoryOfProduct(Category category, int productId) throws ProductNotFoundException;

    public List<ProductResponseDto> allProductsOfACategory(Category category);

    public List<ProductResponseDto> productsOfACategoryHavePriceGreaterThanK(Category category, int price);

    public List<ProductResponseDto> top5CheapestProduct();

    public List<ProductResponseDto> top5CostliestProduct();

    public List<ProductResponseDto> productsBySellerEmailId(String emailId) throws SellerNotFoundException;

    public List<ProductResponseDto> outOfStockProduct();
}
