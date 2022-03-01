package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;

public class ProdInventoryWebclientFactory implements InventoryWebclientFactory {
    private final InventoryWebclient inventoryWebclient;

    public ProdInventoryWebclientFactory(InventoryWebclient inventoryWebclient) {
        this.inventoryWebclient = inventoryWebclient;
    }
    @Override
    public InventoryWebclient getInventoryWebclient(String webclientType){
        return inventoryWebclient;
    }
}
