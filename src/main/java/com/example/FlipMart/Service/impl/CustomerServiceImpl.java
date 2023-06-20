package com.example.FlipMart.Service.impl;

import com.example.FlipMart.Dto.RequestDto.CustomerRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CustomerResponseDto;
import com.example.FlipMart.Enum.Gender;
import com.example.FlipMart.Model.Cart;
import com.example.FlipMart.Model.Customer;
import com.example.FlipMart.Repository.CustomerRepository;
import com.example.FlipMart.Service.CustomerService;
import com.example.FlipMart.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        // dto -> entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer);  // save both customer and cart

        // prepare response Dto
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);
    }

    @Override
    public List<CustomerResponseDto> femaleCustomer() {

        List<Customer> customers = customerRepository.findByGender(Gender.FEMALE);

        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            customerResponseDtoList.add(customerResponseDto);
        }

        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> maleCustomer() {

        List<Customer> customers = customerRepository.findByGender(Gender.MALE);

        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            customerResponseDtoList.add(customerResponseDto);
        }

        return customerResponseDtoList;
    }

    @Override
    public List<CustomerResponseDto> customersOrderedKOrders(int k) {

        List<Customer> customers = customerRepository.findAll();

        List<CustomerResponseDto> customerResponseDtoList = new ArrayList<>();

        for(Customer customer : customers){
            if(customer.getOrders().size() >= k){
                CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
                customerResponseDtoList.add(customerResponseDto);
            }
        }

        return customerResponseDtoList;
    }
}
