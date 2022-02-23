package com.lessonsbyleo.practicerepo.service;

import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class InventoryWebclientTestDouble implements InventoryWebclient {
    private final InventoryWebclient inventoryWebclientMock;
    private final StubbedInventoryWebclient stubbedInventoryWebclient;

    public InventoryWebclientTestDouble(StubbedInventoryWebclient stubbedInventoryWebclient, InventoryWebclient inventoryWebclientMock) {
        this.stubbedInventoryWebclient = stubbedInventoryWebclient;
        this.inventoryWebclientMock = inventoryWebclientMock;
    }

    public InventoryWebclient getInventoryWebclientMock() {
        return inventoryWebclientMock;
    }

    @Override
    public Map<String, Item> getInventory(List<String> skus) {
        Map<String, Item> inventory = stubbedInventoryWebclient.getInventory(skus);
        when(inventoryWebclientMock.getInventory(any())).thenReturn(inventory);
        return inventoryWebclientMock.getInventory(skus);
    }

    public Map<String, Item> getInventoryWithoutMock(List<String> skus) {
        return stubbedInventoryWebclient.getInventory(skus);
    }
}
