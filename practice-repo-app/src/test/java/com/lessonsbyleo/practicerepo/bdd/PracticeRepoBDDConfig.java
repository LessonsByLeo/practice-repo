package com.lessonsbyleo.practicerepo.bdd;

import com.lessonsbyleo.practicerepo.data.ShoppingCartScenarioData;
import com.lessonsbyleo.practicerepo.service.InventoryWebclientTestDouble;
import com.lessonsbyleo.practicerepo.service.SalesTaxWebClientTestDouble;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@ComponentScan(basePackages = {"com.lessonsbyleo.practicerepo"})
@Configuration
public class PracticeRepoBDDConfig {
    @Bean
    public SalesTaxWebclient internalWebclient(StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient){
        SalesTaxWebclient salesTaxWebclientMock = Mockito.mock(SalesTaxWebclient.class);
        return new SalesTaxWebClientTestDouble(stubbedInternalSalesTaxWebclient, salesTaxWebclientMock);
    }

    @Bean
    public SalesTaxWebclient externalWebclient(StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient){
        SalesTaxWebclient salesTaxWebclientMock = Mockito.mock(SalesTaxWebclient.class);
        return new SalesTaxWebClientTestDouble(stubbedExternalSalesTaxWebclient, salesTaxWebclientMock);
    }

    @Bean
    public InventoryWebclient inventoryWebclient(StubbedInventoryWebclient stubbedInventoryWebclient){
        InventoryWebclient inventoryWebclientMock = Mockito.mock(InventoryWebclient.class);
        return new InventoryWebclientTestDouble(stubbedInventoryWebclient, inventoryWebclientMock);
    }

    @Bean
    public Map<String, ShoppingCartScenarioData> shoppingCartScenarioDataMap(){
        return new HashMap<>();
    }

}
