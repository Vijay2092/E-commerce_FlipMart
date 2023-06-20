package com.example.FlipMart.Controller;


import com.example.FlipMart.Dto.RequestDto.CustomerRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CustomerResponseDto;
import com.example.FlipMart.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
    }


    // get all female customers
    @GetMapping("/female-customer")
    public ResponseEntity femaleCustomer(){

        List<CustomerResponseDto> customerResponseDtoList = customerService.femaleCustomer();
        return new ResponseEntity(customerResponseDtoList, HttpStatus.FOUND);
    }

    // get all male customers
    @GetMapping("/male-customer")
    public ResponseEntity maleCustomer(){

        List<CustomerResponseDto>customerResponseDtoList = customerService.maleCustomer();
        return new ResponseEntity(customerResponseDtoList, HttpStatus.FOUND);
    }

    // customers who have ordered atleast k orders
    @GetMapping("/customers-ordered-k-orders/{k}")
    public ResponseEntity customersOrderedKOrders(@PathVariable int k){

        List<CustomerResponseDto>customerResponseDtoList = customerService.customersOrderedKOrders(k);
        return new ResponseEntity(customerResponseDtoList, HttpStatus.FOUND);
    }

}