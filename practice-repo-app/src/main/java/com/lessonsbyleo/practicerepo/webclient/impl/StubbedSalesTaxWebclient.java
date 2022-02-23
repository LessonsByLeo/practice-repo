package com.lessonsbyleo.practicerepo.webclient.impl;

import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;

import java.util.Map;

public class StubbedSalesTaxWebclient implements SalesTaxWebclient {

    private Map<String, Double> stubbedMap;

    public StubbedSalesTaxWebclient(Map<String, Double> stubbedMap) {
        this.stubbedMap = stubbedMap;
    }

    @Override
    public Double getSalesTax(String zipCode) {
        return stubbedMap.get(zipCode);
    }
}
