package com.lessonsbyleo.practicerepo.service;

import com.lessonsbyleo.practicerepo.exception.ZipCodeUnavailableException;
import com.lessonsbyleo.practicerepo.webclient.factory.DevSalesTaxWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.factory.SalesTaxWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SalesTaxService {
    @Autowired
    private SalesTaxWebclientFactory salesTaxWebclientFactory;

    public double getSalesTax(String zipCode, String webclientType) throws ZipCodeUnavailableException {
        Double salesTax;
        SalesTaxWebclient internalWebclient = salesTaxWebclientFactory.getInternalSalesTaxWebclient(webclientType);
        salesTax = internalWebclient.getSalesTax(zipCode);
        if(Objects.isNull(salesTax)){
            SalesTaxWebclient externalWebclient = salesTaxWebclientFactory.getExternalSalesTaxWebclient(webclientType);
            salesTax = externalWebclient.getSalesTax(zipCode);
        }
        if(Objects.isNull(salesTax)){
            throw new ZipCodeUnavailableException(zipCode);
        }
        return salesTax;
    }
}
