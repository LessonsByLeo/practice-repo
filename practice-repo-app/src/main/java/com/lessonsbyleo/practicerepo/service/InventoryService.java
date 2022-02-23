package com.lessonsbyleo.practicerepo.service;

import com.lessonsbyleo.practicerepo.data.InvalidItem;
import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.data.Order;
import com.lessonsbyleo.practicerepo.exception.InvalidOrderException;
import com.lessonsbyleo.practicerepo.util.OrderUtil;
import com.lessonsbyleo.practicerepo.webclient.factory.InventoryWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.lessonsbyleo.practicerepo.exception.InvalidOrderException.NOT_ENOUGH_INVENTORY;
import static com.lessonsbyleo.practicerepo.exception.InvalidOrderException.UNKNOWN_SKU;

@Service
public class InventoryService {
    @Autowired
    private InventoryWebclientFactory inventoryWebclientFactory;

    public Order validateAndUpdateOrder(Order order, String webclientType) throws InvalidOrderException {
        Map<String,Item> inventory = getInventory(order, webclientType);
        List<Item> orderItems = order.getItems();
        List<InvalidItem> invalidItems = new ArrayList<>();
        validateAndUpdateItems(orderItems, inventory, invalidItems);
        return new Order(order.getCustomer(), orderItems);
    }

    private void validateAndUpdateItems(List<Item> orderItems, Map<String, Item> inventory, List<InvalidItem> invalidItems) throws InvalidOrderException {
        for(Item orderItem: orderItems){
            validateAndUpdateItem(orderItem, inventory, invalidItems);
        }

        if(invalidItems.size() > 0){
            throw new InvalidOrderException(invalidItems);
        }
    }

    private void validateAndUpdateItem(Item orderItem, Map<String, Item> inventory, List<InvalidItem> invalidItems){
        String sku = orderItem.getSku();
        Item inventoryItem = inventory.get(sku);
        if(Objects.isNull(inventoryItem)){
            invalidItems.add(new InvalidItem(sku, UNKNOWN_SKU));
        }else if(orderItem.getQuantity() > inventoryItem.getQuantity()){
            invalidItems.add(new InvalidItem(sku, NOT_ENOUGH_INVENTORY));
        } else {
            orderItem.setCost(inventoryItem.getCost());
        }
    }

    private Map<String,Item> getInventory(Order order, String webclientType){
        List<String> orderSkus = OrderUtil.getOrderSkus(order);
        InventoryWebclient inventoryWebclient = inventoryWebclientFactory.getInventoryWebclient(webclientType);
        return inventoryWebclient.getInventory(orderSkus);
    }
}
