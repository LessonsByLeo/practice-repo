package com.lessonsbyleo.practicerepo.exception;

import com.lessonsbyleo.practicerepo.data.InvalidItem;

import java.util.List;

public class ShoppingCartException extends Exception {
    private List<InvalidItem> invalidItems;

    public ShoppingCartException(InvalidOrderException invalidOrderException) {
        super(invalidOrderException.getMessage());
        this.invalidItems = invalidOrderException.getInvalidItems();
    }

    public ShoppingCartException(ZipCodeUnavailableException zipCodeUnavailableException){
        super(zipCodeUnavailableException.getMessage());
    }

    public List<InvalidItem> getInvalidItems() {
        return invalidItems;
    }

    public void setInvalidItems(List<InvalidItem> invalidItems) {
        this.invalidItems = invalidItems;
    }
}
