package com.lessonsbyleo.practicerepo.util;

import com.lessonsbyleo.practicerepo.data.Address;
import com.lessonsbyleo.practicerepo.data.Customer;
import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderCreator {

    public Order createTestOrder(int numOfItems, String zipCode){
        Order order = new Order();
        List<Item> items = createTestItems(numOfItems);
        order.setItems(items);
        Customer customer = createTestCustomer(zipCode);
        order.setCustomer(customer);
        return order;
    }

    private List<Item> createTestItems(int numOfItems){
        List<Item> items = new ArrayList<>();
        for(int i = 0; i < numOfItems; i++){
            Item item = new Item();
            item.setSku("sku_" + i);
            item.setDescription("item num: " + (i + 1));
            item.setQuantity(i + 2);
            items.add(item);
        }
        return items;
    }

    private Customer createTestCustomer(String zipCode) {
        Customer customer = new Customer();
        customer.setFirstName("firstName");
        customer.setLastName("lastName");

        Address address = new Address();
        address.setCity("city");
        address.setStreet("street");
        address.setState("state");
        address.setZipCode(zipCode);
        customer.setAddress(address);
        return customer;
    }
}
