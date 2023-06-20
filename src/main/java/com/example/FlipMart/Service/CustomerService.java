package com.example.FlipMart.Service;

import com.example.FlipMart.Dto.RequestDto.CustomerRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    public List<CustomerResponseDto> femaleCustomer();

    public List<CustomerResponseDto> maleCustomer();

    public List<CustomerResponseDto> customersOrderedKOrders(int k);
}
