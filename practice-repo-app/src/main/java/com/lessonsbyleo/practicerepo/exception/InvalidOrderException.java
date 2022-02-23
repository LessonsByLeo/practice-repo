package com.lessonsbyleo.practicerepo.exception;

import com.lessonsbyleo.practicerepo.data.InvalidItem;

import java.util.List;

public class InvalidOrderException extends Exception {
    private final List<InvalidItem> invalidItems;
    public static final String INVALID_ORDER = "Invalid Order";
    public static final String UNKNOWN_SKU = "Unknown sku";
    public static final String NOT_ENOUGH_INVENTORY = "Not enough inventory for sku";


    public InvalidOrderException(List<InvalidItem> invalidItems) {
        super(INVALID_ORDER);
        this.invalidItems = invalidItems;
    }

    public List<InvalidItem> getInvalidItems() {
        return invalidItems;
    }
    
}
