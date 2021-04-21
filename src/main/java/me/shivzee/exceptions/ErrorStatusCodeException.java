package me.shivzee.exceptions;

public class ErrorStatusCodeException extends Exception {
    public ErrorStatusCodeException(String errorMessage){
        super(errorMessage);
    }
}
