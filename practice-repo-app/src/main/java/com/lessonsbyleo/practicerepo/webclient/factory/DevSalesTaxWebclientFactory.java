package com.lessonsbyleo.practicerepo.webclient.factory;

import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.lessonsbyleo.practicerepo.util.ShoppingCartConstants.STUBBED_WEBCLIENT;

@Component
public class SalesTaxWebclientFactory {
    @Autowired(required = false)
    private StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient;

    @Autowired(required = false)
    private StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient;

    @Autowired
    private SalesTaxWebclient internalWebclient;

    @Autowired
    private SalesTaxWebclient externalWebclient;

    public SalesTaxWebclient getInternalSalesTaxWebclient(String webclientType) {
        if (webclientType.equals(STUBBED_WEBCLIENT)) {
            return stubbedInternalSalesTaxWebclient;
        } else {
            return internalWebclient;
        }
    }

    public SalesTaxWebclient getExternalSalesTaxWebclient(String webclientType) {
        if (webclientType.equals(STUBBED_WEBCLIENT)) {
            return stubbedExternalSalesTaxWebclient;
        } else {
            return externalWebclient;
        }
    }
}
