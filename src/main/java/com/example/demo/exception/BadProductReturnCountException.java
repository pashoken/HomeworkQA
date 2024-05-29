package com.example.demo.exception;

public class BadProductReturnCountException extends RuntimeException {
    public BadProductReturnCountException(String message) {
        super(message);
    }
}