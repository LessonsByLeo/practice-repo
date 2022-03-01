package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;

public interface InventoryWebclientFactory {
    InventoryWebclient getInventoryWebclient(String webclientType);
}
