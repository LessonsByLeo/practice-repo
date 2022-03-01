package com.lessonsbyleo.practicerepo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.webclient.config.StubbedInventoryConfiguration;
import com.lessonsbyleo.practicerepo.webclient.config.StubbedSalesTaxConfiguration;
import com.lessonsbyleo.practicerepo.webclient.factory.DevInventoryWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.factory.DevSalesTaxWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.factory.InventoryWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.factory.SalesTaxWebclientFactory;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.InventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.inf.SalesTaxWebclient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Configuration
@Profile("stubbed")
public class StubbedPracticeRepoConfig {
    private static final Logger logger = LoggerFactory.getLogger(StubbedPracticeRepoConfig.class);

    @Bean
    public StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient(StubbedSalesTaxConfiguration stubbedSalesTaxConfiguration,
                                                                     ObjectWriter objectWriter) throws JsonProcessingException {
        Map<String, Double> stubbedMap = stubbedSalesTaxConfiguration.getInternalStubMap();
        logger.debug("stubbedInternalSalesTaxWebclient Map:\n{}",objectWriter.writeValueAsString(stubbedMap));
        return new StubbedSalesTaxWebclient(stubbedMap);
    }

    @Bean
    public StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient(StubbedSalesTaxConfiguration stubbedSalesTaxConfiguration,
                                                                     ObjectWriter objectWriter) throws JsonProcessingException {
        Map<String, Double> stubbedMap = stubbedSalesTaxConfiguration.getExternalStubMap();
        logger.info("stubbedExternalSalesTaxWebclient Map:\n{}",objectWriter.writeValueAsString(stubbedMap));
        return new StubbedSalesTaxWebclient(stubbedMap);
    }

    @Bean
    public StubbedInventoryWebclient stubbedInventoryWebclient(StubbedInventoryConfiguration stubbedInventoryConfiguration,
                                                               ObjectWriter objectWriter) throws JsonProcessingException {
        Map<String, Item> stubbedInventoryMap = stubbedInventoryConfiguration.getStubMap();
        logger.info("\n{}",objectWriter.writeValueAsString(stubbedInventoryMap));
        return new StubbedInventoryWebclient(stubbedInventoryMap);
    }


}
