package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;

import static com.lessonsbyleo.practicerepo.util.ShoppingCartConstants.STUBBED_WEBCLIENT;

public class DevSalesTaxWebclientFactory implements SalesTaxWebclientFactory {
    private final StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient;
    private final StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient;

    private final SalesTaxWebclient internalWebclient;
    private final SalesTaxWebclient externalWebclient;

    public DevSalesTaxWebclientFactory(StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient,
                                       StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient,
                                       SalesTaxWebclient internalWebclient,
                                       SalesTaxWebclient externalWebclient) {
        this.stubbedInternalSalesTaxWebclient = stubbedInternalSalesTaxWebclient;
        this.stubbedExternalSalesTaxWebclient = stubbedExternalSalesTaxWebclient;
        this.internalWebclient = internalWebclient;
        this.externalWebclient = externalWebclient;
    }

    @Override
    public SalesTaxWebclient getInternalSalesTaxWebclient(String webclientType) {
        if (webclientType.equals(STUBBED_WEBCLIENT)) {
            return stubbedInternalSalesTaxWebclient;
        } else {
            return internalWebclient;
        }
    }

    @Override
    public SalesTaxWebclient getExternalSalesTaxWebclient(String webclientType) {
        if (webclientType.equals(STUBBED_WEBCLIENT)) {
            return stubbedExternalSalesTaxWebclient;
        } else {
            return externalWebclient;
        }
    }
}
