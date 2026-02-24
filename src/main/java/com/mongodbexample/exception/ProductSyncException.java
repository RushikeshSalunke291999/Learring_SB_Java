package com.mongodbexample.exception;

public class ProductSyncException extends RuntimeException{
    public ProductSyncException(String message) {
        super(message);
    }
}
