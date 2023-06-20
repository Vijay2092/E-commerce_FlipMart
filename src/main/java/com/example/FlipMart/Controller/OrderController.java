package com.example.FlipMart.Controller;

import com.example.FlipMart.Dto.RequestDto.OrderRequestDto;
import com.example.FlipMart.Dto.ResponseDto.OrderResponseDto;
import com.example.FlipMart.Service.OrderService;
import com.example.FlipMart.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){

        try{
            OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // get top 5 orders with highest order value
    @GetMapping("/top-5-order-with-highest-order-value")
    public ResponseEntity top5OrderWithHighestValue(){

        List<OrderResponseDto> orderResponseDtos = orderService.top5OrderWithHighestValue();
        return new ResponseEntity(orderResponseDtos, HttpStatus.FOUND);
    }

    //all the orders of a particular customer
    @GetMapping("/all-the-orders-of-a-customer/{emailId}")
    public ResponseEntity allOrdersOfACustomer(@PathVariable String emailId){

        try {
            List<OrderResponseDto> orderResponseDtos = orderService.allOrdersOfACustomer(emailId);
            return new ResponseEntity(orderResponseDtos, HttpStatus.FOUND);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    // top 5 orders of a customer based on cost
    @GetMapping("/top-5-highest-value-orders-of-a-customer/{emailId}")
    public ResponseEntity top5HighestValueOrdersOfACustomer(@PathVariable String emailId){

        try {
            List<OrderResponseDto> orderResponseDtos = orderService.top5HighestValueOrdersOfACustomer(emailId);
            return new ResponseEntity(orderResponseDtos, HttpStatus.FOUND);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // top 5 recently ordered orders from customer
    @GetMapping("/top-5-recent-orders-of-a-customer/{emailId}")
    public ResponseEntity top5RecentOrdersOfACustomer(@PathVariable String emailId){

        try {
            List<OrderResponseDto> orderResponseDtos = orderService.top5RecentOrdersOfACustomer(emailId);
            return new ResponseEntity(orderResponseDtos, HttpStatus.FOUND);
        } catch (CustomerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}