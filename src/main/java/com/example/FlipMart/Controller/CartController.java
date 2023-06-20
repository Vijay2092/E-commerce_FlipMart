package com.example.FlipMart.Controller;


import com.example.FlipMart.Dto.RequestDto.CheckOutCartRequestDto;
import com.example.FlipMart.Dto.RequestDto.ItemRequestDto;
import com.example.FlipMart.Dto.ResponseDto.CartResponseDto;
import com.example.FlipMart.Dto.ResponseDto.OrderResponseDto;
import com.example.FlipMart.Model.Item;
import com.example.FlipMart.Service.CartService;
import com.example.FlipMart.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){

        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item, itemRequestDto);
            return new ResponseEntity(cartResponseDto, HttpStatus.ACCEPTED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkOutCart(@RequestBody CheckOutCartRequestDto checkOutCartRequestDto){

        try {
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkOutCartRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // add the functionality of email sending in direct order and checkout order
    // kunaljindal995@gmail.com

    // integrate swagger

}
