package com.lessonsbyleo.practicerepo.webclient.impl;

import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class StubbedInventoryWebclient implements InventoryWebclient {
    private final Map<String, Item> stubbedMap;

    public StubbedInventoryWebclient(Map<String, Item> stubbedMap) {
        this.stubbedMap = stubbedMap;
    }

    @Override
    public Map<String, Item> getInventory(List<String> skus) {
        Map<String, Item> skuInventory = new HashMap<>();
        for (String sku: skus){
            Item stubbedInventoryItem = stubbedMap.get(sku);
            Item inventoryItem = null;
            if(Objects.nonNull(stubbedInventoryItem)){
                inventoryItem = new Item(stubbedInventoryItem);
            }
            skuInventory.put(sku, inventoryItem);
        }
        return skuInventory;
    }
}
