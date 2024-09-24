package com.dreamcart.dreamshop.exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}
