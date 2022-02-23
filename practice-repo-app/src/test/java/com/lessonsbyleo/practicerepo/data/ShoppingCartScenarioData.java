package com.lessonsbyleo.practicerepo.data;

import com.lessonsbyleo.practicerepo.exception.ShoppingCartException;

public class ShoppingCartScenarioData {
    private Order order;
    private TotalBill totalBill;
    private ShoppingCartException shoppingCartException;
    private String expectedShoppingCartExceptionMessage;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TotalBill getTotalBill() {
        return totalBill;
    }

    public void setTotalBill(TotalBill totalBill) {
        this.totalBill = totalBill;
    }

    public ShoppingCartException getShoppingCartException() {
        return shoppingCartException;
    }

    public void setShoppingCartException(ShoppingCartException shoppingCartException) {
        this.shoppingCartException = shoppingCartException;
    }

    public String getExpectedShoppingCartExceptionMessage() {
        return expectedShoppingCartExceptionMessage;
    }

    public void setExpectedShoppingCartExceptionMessage(String expectedShoppingCartExceptionMessage) {
        this.expectedShoppingCartExceptionMessage = expectedShoppingCartExceptionMessage;
    }
}
