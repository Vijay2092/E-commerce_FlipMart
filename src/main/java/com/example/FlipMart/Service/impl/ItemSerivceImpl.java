package com.example.FlipMart.Service.impl;

import com.example.FlipMart.Dto.RequestDto.ItemRequestDto;
import com.example.FlipMart.Model.Customer;
import com.example.FlipMart.Model.Item;
import com.example.FlipMart.Model.Product;
import com.example.FlipMart.Repository.CustomerRepository;
import com.example.FlipMart.Repository.ProductRepository;
import com.example.FlipMart.Service.ItemService;
import com.example.FlipMart.exception.CustomerNotFoundException;
import com.example.FlipMart.exception.InsufficientQuantityException;
import com.example.FlipMart.exception.OutOfStockException;
import com.example.FlipMart.exception.ProductNotFoundException;
import com.example.FlipMart.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemSerivceImpl implements ItemService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product does not exist!!!");
        }

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer doesn't exist!!");
        }

        Product product = productOptional.get();

        if(product.getQuantity() == 0){
            throw new OutOfStockException("Product is Out Of Stock!!");
        }

        if(product.getQuantity() < itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available.");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());

        return item;
    }
}