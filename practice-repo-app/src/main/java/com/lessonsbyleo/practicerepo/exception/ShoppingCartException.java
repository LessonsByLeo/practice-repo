package com.lessonsbyleo.practicerepo.exception;

public class ShoppingCartException extends Exception {
    private InvalidOrderException invalidOrderException;
    private ZipCodeUnavailableException zipCodeUnavailableException;

    public ShoppingCartException(InvalidOrderException invalidOrderException) {
        super(invalidOrderException.getMessage());
        this.invalidOrderException = invalidOrderException;
    }

    public ShoppingCartException(ZipCodeUnavailableException zipCodeUnavailableException){
        super(zipCodeUnavailableException.getMessage());
        this.zipCodeUnavailableException = zipCodeUnavailableException;
    }

    public InvalidOrderException getInvalidOrderException() {
        return invalidOrderException;
    }
}
