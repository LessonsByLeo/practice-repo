package com.lessonsbyleo.practicerepo.util;

import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.TotalBill;

import java.util.List;

public class ShoppingCartCalculator {

    private static final int CENTS_IN_DOLLAR = 100;

    public static TotalBill calculate(TotalBill totalBill){
        double total = 0.0;
        List<Item> items = totalBill.getOrder().getItems();
        for(Item item: items){
            total += item.getCost() * item.getQuantity();
        }
        total *= totalBill.getSalesTax();
        total = roundToDollarAmount(total);
        totalBill.setTotal(total);
        return totalBill;
    }

    private static double roundToDollarAmount(double total) {
        double numOfCents = total * CENTS_IN_DOLLAR;
        long wholeNumOfCents = (long) numOfCents;
        return (double) wholeNumOfCents / CENTS_IN_DOLLAR;
    }
}
