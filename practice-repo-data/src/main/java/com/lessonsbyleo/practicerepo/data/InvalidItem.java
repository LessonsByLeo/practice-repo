package com.lessonsbyleo.practicerepo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InvalidItem {
    @JsonProperty
    private String sku;
    @JsonProperty
    private String invalidDescription;

    public InvalidItem() {
    }

    public InvalidItem(String sku, String invalidDescription) {
        this.sku = sku;
        this.invalidDescription = invalidDescription;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getInvalidDescription() {
        return invalidDescription;
    }

    public void setInvalidDescription(String invalidDescription) {
        this.invalidDescription = invalidDescription;
    }

    @Override
    public String toString() {
        return "InvalidItem{" +
                "sku='" + sku + '\'' +
                ", invalidDescription='" + invalidDescription + '\'' +
                '}';
    }
}
