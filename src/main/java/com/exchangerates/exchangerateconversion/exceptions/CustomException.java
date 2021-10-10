package com.exchangerates.exchangerateconversion.exceptions;

@SuppressWarnings("serial")
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
