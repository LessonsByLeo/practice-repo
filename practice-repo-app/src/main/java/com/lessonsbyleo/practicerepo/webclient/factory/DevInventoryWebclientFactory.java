package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;

import static com.lessonsbyleo.practicerepo.util.ShoppingCartConstants.STUBBED_WEBCLIENT;

public class DevInventoryWebclientFactory implements InventoryWebclientFactory {
    private final StubbedInventoryWebclient stubbedInventoryWebclient;
    private final InventoryWebclient inventoryWebclient;

    public DevInventoryWebclientFactory(StubbedInventoryWebclient stubbedInventoryWebclient, InventoryWebclient inventoryWebclient) {
        this.stubbedInventoryWebclient = stubbedInventoryWebclient;
        this.inventoryWebclient = inventoryWebclient;
    }

    @Override
    public InventoryWebclient getInventoryWebclient(String webclientType){
        if(webclientType.equals(STUBBED_WEBCLIENT)){
            return stubbedInventoryWebclient;
        } else {
            return inventoryWebclient;
        }
    }
}
