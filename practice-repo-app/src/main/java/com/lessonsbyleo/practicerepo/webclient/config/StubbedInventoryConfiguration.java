package com.lessonsbyleo.practicerepo.webclient.config;

import com.lessonsbyleo.practicerepo.data.Item;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Map;

@Profile("stubbed")
@Component
@ConfigurationProperties(prefix = "inventory")
public class StubbedInventoryConfiguration {
    private Map<String, Item> stubMap;

    public void setStubMap(Map<String, Item> stubMap) {
        this.stubMap = stubMap;
    }

    public Map<String, Item> getStubMap() {
        return stubMap;
    }
}
