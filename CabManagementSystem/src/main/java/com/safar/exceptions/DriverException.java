package com.safar.exceptions;

public class DriverException extends RuntimeException{
    public DriverException(){}
    public DriverException(String message){
        super(message);
    }
}
