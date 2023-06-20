package com.example.FlipMart.Service.impl;

import com.example.FlipMart.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.FlipMart.Dto.RequestDto.ItemRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CartResponseDto;
import com.example.FlipMart.Dto.ResponseDto.OrderResponseDto;
import com.example.FlipMart.Model.*;
import com.example.FlipMart.Repository.*;
import com.example.FlipMart.Service.CartService;
import com.example.FlipMart.Service.OrderService;
import com.example.FlipMart.exception.CustomerNotFoundException;
import com.example.FlipMart.exception.EmptyCartException;
import com.example.FlipMart.exception.InsufficientQuantityException;
import com.example.FlipMart.exception.InvalidCardException;
import com.example.FlipMart.transformer.CartTransformer;
import com.example.FlipMart.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();

        cart.setCartTotal(cart.getCartTotal()+ item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart);  // saves both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);

        product.getItems().add(savedItem);

        //prepare the response dto
        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    @Override
    public OrderResponseDto checkOutCart(CheckOutCartRequestDto checkOutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {

        // validate customer
        Customer customer = customerRepository.findByEmailId(checkOutCartRequestDto.getEmailId());
        if(customer == null){
            throw new CustomerNotFoundException("Customer does not exist!!!");
        }

        // validate Card
        Card card = cardRepository.findByCardNo(checkOutCartRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv() != checkOutCartRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Card is invalid!!");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().size() == 0){
            throw new EmptyCartException("Cart is Empty!!");
        }

        try {
            OrderEntity order = orderService.placeOrder(cart, card);
            resetCart(cart);

            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);
            return OrderTransformer.OrderToOrderResponseDto(savedOrder);
        }
        catch (InsufficientQuantityException e){
            throw e;
        }

    }

    private void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }


}