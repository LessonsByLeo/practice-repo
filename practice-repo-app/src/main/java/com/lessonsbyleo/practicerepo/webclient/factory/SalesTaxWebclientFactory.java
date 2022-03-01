package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;

public interface SalesTaxWebclientFactory {
    SalesTaxWebclient getInternalSalesTaxWebclient(String webclientType);
    SalesTaxWebclient getExternalSalesTaxWebclient(String webclientType);
}
