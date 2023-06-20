package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.SellerRequestDto;
import com.example.FlipMart.Dto.ResponseDto.SellerResponseDto;
import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.exception.SellerNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface SellerService {
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    public SellerResponseDto updateInfoByEmail(String emailId, String name) throws SellerNotFoundException;

    public List<SellerResponseDto> sellerSellProductOfCategory(Category category);

    public HashMap<String, Integer> allProductsSoldByASeller(String emailId) throws SellerNotFoundException;

    public SellerResponseDto sellerWithHighestNoOfProducts();

    public SellerResponseDto sellerWithMinimumNoOfProducts();

    public SellerResponseDto sellerSellingCostliestProduct();

    public SellerResponseDto sellerSellingCheapestProduct();

}
