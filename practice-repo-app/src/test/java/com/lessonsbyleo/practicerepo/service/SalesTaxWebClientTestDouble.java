package com.lessonsbyleo.practicerepo.service;

import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;

import static org.mockito.Mockito.when;

public class SalesTaxWebClientTestDouble implements SalesTaxWebclient {
    private final SalesTaxWebclient salesTaxWebclientMock;
    private final StubbedSalesTaxWebclient stubbedSalesTaxWebclient;

    public SalesTaxWebClientTestDouble(StubbedSalesTaxWebclient stubbedSalesTaxWebclient, SalesTaxWebclient salesTaxWebclientMock) {
        this.salesTaxWebclientMock = salesTaxWebclientMock;
        this.stubbedSalesTaxWebclient = stubbedSalesTaxWebclient;
    }

    public SalesTaxWebclient getSalesTaxWebclientMock() {
        return salesTaxWebclientMock;
    }

    @Override
    public Double getSalesTax(String zipCode) {
        Double salesTax = stubbedSalesTaxWebclient.getSalesTax(zipCode);
        when(salesTaxWebclientMock.getSalesTax(zipCode)).thenReturn(salesTax);
        return salesTaxWebclientMock.getSalesTax(zipCode);
    }

    public Double getSalesTaxWithoutMock(String zipCode){
        return stubbedSalesTaxWebclient.getSalesTax(zipCode);
    }
}
