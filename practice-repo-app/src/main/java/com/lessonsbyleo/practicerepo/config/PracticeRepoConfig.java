package com.lessonsbyleo.practicerepo.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.lessonsbyleo.practicerepo.data.Item;
import com.lessonsbyleo.practicerepo.webclient.config.StubbedInventoryConfiguration;
import com.lessonsbyleo.practicerepo.webclient.config.StubbedSalesTaxConfiguration;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedInventoryWebclient;
import com.lessonsbyleo.practicerepo.webclient.impl.StubbedSalesTaxWebclient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Map;

@Configuration
public class PracticeRepoConfig {
    Logger logger = LoggerFactory.getLogger(PracticeRepoConfig.class);

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ObjectWriter objectWriter(ObjectMapper objectMapper){
        return objectMapper.writerWithDefaultPrettyPrinter();
    }

    @Profile("stubbed")
    @Bean
    public StubbedSalesTaxWebclient stubbedInternalSalesTaxWebclient(StubbedSalesTaxConfiguration stubbedSalesTaxConfiguration,
                                                                     ObjectWriter objectWriter) throws JsonProcessingException {
        Map<String, Double> stubbedMap = stubbedSalesTaxConfiguration.getInternalStubMap();
        logger.debug("stubbedInternalSalesTaxWebclient Map:\n{}",objectWriter.writeValueAsString(stubbedMap));
        return new StubbedSalesTaxWebclient(stubbedMap);
    }

    @Profile("stubbed")
    @Bean
    public StubbedSalesTaxWebclient stubbedExternalSalesTaxWebclient(StubbedSalesTaxConfiguration stubbedSalesTaxConfiguration,
                                                                     ObjectWriter objectWriter) throws JsonProcessingException {
        Map<String, Double> stubbedMap = stubbedSalesTaxConfiguration.getExternalStubMap();
        logger.info("stubbedExternalSalesTaxWebclient Map:\n{}",objectWriter.writeValueAsString(stubbedMap));
        return new StubbedSalesTaxWebclient(stubbedMap);
    }

    @Profile("stubbed")
    @Bean
    public StubbedInventoryWebclient stubbedInventoryWebclient(StubbedInventoryConfiguration stubbedInventoryConfiguration,
                                                               ObjectWriter objectWriter) throws JsonProcessingException {
        Map<String, Item> stubbedInventoryMap = stubbedInventoryConfiguration.getStubMap();
        logger.info("\n{}",objectWriter.writeValueAsString(stubbedInventoryMap));
        return new StubbedInventoryWebclient(stubbedInventoryMap);
    }
}
