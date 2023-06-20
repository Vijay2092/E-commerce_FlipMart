package com.example.FlipMart.Controller;

import com.example.FlipMart.Dto.RequestDto.ProductRequestDto;
import com.example.FlipMart.Dto.ResponseDto.ProductResponseDto;
import com.example.FlipMart.Enum.Category;
import com.example.FlipMart.Service.ProductService;
import com.example.FlipMart.exception.ProductNotFoundException;
import com.example.FlipMart.exception.SellerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductsByCategoryAndPrice(@PathVariable("category") Category category, @PathVariable("price") int price){

        List<ProductResponseDto> productResponseDtos = productService.getAllProductsByCategoryAndPrice(category, price);
        return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
    }

    // change the category of a product
    @PutMapping("/change-category-of-a-product")
    public ResponseEntity changeCategoryOfProduct(@RequestParam Category category, @RequestParam int productId) throws ProductNotFoundException {
        productService.changeCategoryOfProduct(category, productId);
        return new ResponseEntity("the category has been changed", HttpStatus.CREATED);
    }

    // get all the products of a category
    @GetMapping("/all-products-of-a-category")
    public ResponseEntity allProductsOfACategory(@RequestParam Category category){
        List<ProductResponseDto> productResponseDtos = productService.allProductsOfACategory(category);
        return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
    }

    // get all the products in a category who have price greater than 500
    @GetMapping("/all-products-in-a-category-have-price-greater-than-k")
    public ResponseEntity productsOfACategoryHavePriceGreaterThanK(@RequestParam Category category, @RequestParam int price){

        List<ProductResponseDto> productResponseDtos = productService.productsOfACategoryHavePriceGreaterThanK(category, price);
        return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
    }


    // get the top 5 cheapest products in a category
    @GetMapping("/top-5-cheapest-product")
    public ResponseEntity top5CheapestProduct(){

        List<ProductResponseDto> productResponseDtos = productService.top5CheapestProduct();
        return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
    }

    // get top 5 most expensive product in a category
    @GetMapping("/top-5-costliest-product")
    public ResponseEntity top5CostliestProduct(){

        List<ProductResponseDto> productResponseDtos = productService.top5CostliestProduct();
        return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
    }

    // get all the products of seller based on emailId of the seller
    @GetMapping("/products-of-a-seller-by-email/{emailId}")
    public ResponseEntity productsBySellerEmailId(@PathVariable String emailId){
        try {
            List<ProductResponseDto> productResponseDtos = productService.productsBySellerEmailId(emailId);
            return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // get all the out of stock products for a particular category
    @GetMapping("/out-of-stock-products")
    public ResponseEntity outOfStockProduct(){
        List<ProductResponseDto> productResponseDtos = productService.outOfStockProduct();
        return new ResponseEntity(productResponseDtos, HttpStatus.FOUND);
    }

    // send an email to the seller of the product if the product is out of stock


}