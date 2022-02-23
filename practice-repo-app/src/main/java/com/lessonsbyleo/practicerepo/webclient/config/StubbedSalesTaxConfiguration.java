package com.lessonsbyleo.practicerepo.webclient.config;

import com.lessonsbyleo.practicerepo.data.Item;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Profile("stubbed")
@Component
@ConfigurationProperties(prefix = "sales-tax")
public class StubbedSalesTaxConfiguration {
    private Map<String, Double> internalStubMap;
    private Map<String, Double> externalStubMap;

    public void setInternalStubMap(Map<String, Double> internalStubMap) {
        this.internalStubMap = internalStubMap;
    }

    public void setExternalStubMap(Map<String, Double> externalStubMap) {
        this.externalStubMap = externalStubMap;
    }

    public Map<String, Double> getInternalStubMap() {
        return internalStubMap;
    }

    public Map<String, Double> getExternalStubMap() {
        return externalStubMap;
    }
}
