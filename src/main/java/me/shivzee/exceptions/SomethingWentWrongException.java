package me.shivzee.exceptions;

public class SomethingWentWrongException extends Exception {
    public SomethingWentWrongException(String errorMessage){
        super(errorMessage);
    }
}
