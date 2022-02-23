package com.lessonsbyleo.practicerepo.util;

import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderUtil {

    public static List<String> getOrderSkus(Order order) {
        List<String> skus = new ArrayList<>();
        List<Item> items = order.getItems();
        for(Item item: items){
            skus.add(item.getSku());
        }
        return skus;
    }

    public static String getZipcode(Order order){
        return order.getCustomer().getAddress().getZipCode();
    }
}
