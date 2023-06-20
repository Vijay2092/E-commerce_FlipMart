package com.example.FlipMart.Controller;

import com.example.FlipMart.Dto.RequestDto.SellerRequestDto;
import com.example.FlipMart.Dto.ResponseDto.SellerResponseDto;
import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.Service.SellerService;
import com.example.FlipMart.exception.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){

        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }


    // update seller info based on email
    @PutMapping("/update-sellerinfo-based-on-email/{emailId}/name/{name}")
    public ResponseEntity updateInfoByEmail(@PathVariable String emailId, @PathVariable String name) {

        try {
            SellerResponseDto sellerResponseDto = sellerService.updateInfoByEmail(emailId, name);
            return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    // get all the sellers who sell products of a particular category
    @GetMapping("/seller-sell-product-of-a-particular-category/{category}")
    public ResponseEntity sellerSellProductOfCategory(@PathVariable Category category){

        List<SellerResponseDto> sellerResponseDtoList = sellerService.sellerSellProductOfCategory(category);
        return new ResponseEntity(sellerResponseDtoList, HttpStatus.FOUND);
    }

    // get all the products sold by a seller in a category
    @GetMapping("/all-products-sold-by-seller/emailId/{emailId}")
    public ResponseEntity allProductsSoldByASeller(@PathVariable String emailId){

        try {
            HashMap<String, Integer> hashMap = sellerService.allProductsSoldByASeller(emailId);
            return new ResponseEntity(hashMap, HttpStatus.FOUND);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }


    }

    // seller with highest number of products
    @GetMapping("/seller-with-highest-number-of-products")
    public ResponseEntity sellerWithHighestNoOfProducts(){

        SellerResponseDto sellerResponseDto = sellerService.sellerWithHighestNoOfProducts();
        return new ResponseEntity(sellerResponseDto, HttpStatus.FOUND);
    }

    // seller with minimum number of products
    @GetMapping("/seller-with-minimum-number-of-products")
    public ResponseEntity sellerWithMinimumNoOfProducts(){

        SellerResponseDto sellerResponseDto = sellerService.sellerWithMinimumNoOfProducts();
        return new ResponseEntity(sellerResponseDto, HttpStatus.FOUND);
    }

    // seller selling the costliest product
    @GetMapping("/seller-selling-costliest-product")
    public ResponseEntity sellerSellingCostliestProduct(){

        SellerResponseDto sellerResponseDto = sellerService.sellerSellingCostliestProduct();
        return new ResponseEntity(sellerResponseDto, HttpStatus.FOUND);
    }

    // seller selling the cheapest product
    @GetMapping("/seller-selling-cheapest-product")
    public ResponseEntity sellerSellingCheapestProduct(){

        SellerResponseDto sellerResponseDto = sellerService.sellerSellingCheapestProduct();
        return new ResponseEntity(sellerResponseDto, HttpStatus.FOUND);
    }
}
