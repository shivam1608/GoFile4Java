package me.shivzee.exceptions;

public class ValueMissingException extends Exception {
    public ValueMissingException(String errorMessage){
        super(errorMessage);
    }
}
