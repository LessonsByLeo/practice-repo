package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static com.lessonsbyleo.practicerepo.util.ShoppingCartConstants.STUBBED_WEBCLIENT;

@Component
public class InventoryWebclientFactory {
    @Autowired(required = false)
    private StubbedInventoryWebclient stubbedInventoryWebclient;

    @Autowired(required = false)
    private InventoryWebclient inventoryWebclient;

    public InventoryWebclient getInventoryWebclient(String webclientType){
        if(webclientType.equals(STUBBED_WEBCLIENT)){
            return stubbedInventoryWebclient;
        } else {
            return inventoryWebclient;
        }
    }
}
