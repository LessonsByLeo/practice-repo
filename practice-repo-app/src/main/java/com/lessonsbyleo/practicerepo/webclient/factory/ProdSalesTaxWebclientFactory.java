package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;

public class ProdSalesTaxWebclientFactory implements SalesTaxWebclientFactory {
    private final SalesTaxWebclient internalWebclient;
    private final SalesTaxWebclient externalWebclient;

    public ProdSalesTaxWebclientFactory(SalesTaxWebclient internalWebclient, SalesTaxWebclient externalWebclient) {
        this.internalWebclient = internalWebclient;
        this.externalWebclient = externalWebclient;
    }
    @Override
    public SalesTaxWebclient getInternalSalesTaxWebclient(String webclientType) {
        return internalWebclient;

    }
    @Override
    public SalesTaxWebclient getExternalSalesTaxWebclient(String webclientType) {
        return externalWebclient;
    }
}
