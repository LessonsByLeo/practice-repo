package com.lessonsbyleo.practicerepo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.practicerepo.webclient.factory.*;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class PracticeRepoConfig {
    private static final Logger logger = LoggerFactory.getLogger(PracticeRepoConfig.class);

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ObjectWriter objectWriter(ObjectMapper objectMapper){
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Profile("prod")
    @Bean
    public InventoryWebclientFactory prodInventoryWebclientFactory(InventoryWebclient inventoryWebclient){
        return new ProdInventoryWebclientFactory(inventoryWebclient);
    }

    @Profile("prod")
    @Bean
    public SalesTaxWebclientFactory prodSalesTaxWebclientFactory(SalesTaxWebclient internalWebclient, SalesTaxWebclient externalWebclient){
        return new ProdSalesTaxWebclientFactory(internalWebclient, externalWebclient);
    }

    @Profile("!prod & stubbed")
    @Bean
    public InventoryWebclientFactory devInventoryWebclientFactory(StubbedInventoryWebclient stubbedInventoryWebclient, InventoryWebclient inventoryWebclient){
        return new DevInventoryWebclientFactory(stubbedInventoryWebclient, inventoryWebclient);
    }

    @Profile("!prod & stubbed")
    @Bean
    public SalesTaxWebclientFactory devSalesTaxWebclientFactory(StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient,
                                                             StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient,
                                                             SalesTaxWebclient internalWebclient,
                                                             SalesTaxWebclient externalWebclient){
        return new DevSalesTaxWebclientFactory(stubbedInternalSalesTaxWebclient, stubbedExternalSalesTaxWebclient, internalWebclient, externalWebclient);
    }


}
