package com.example.FlipMart.exception;

public class OutOfStockException extends  Exception{
    public OutOfStockException(String message){
        super(message);
    }
}
