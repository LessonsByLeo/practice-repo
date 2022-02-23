package com.lessonsbyleo.practicerepo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {
    @JsonProperty
    private String sku;
    @JsonProperty
    private String description;
    @JsonProperty
    private Double cost;
    @JsonProperty
    private Integer quantity;

    public Item() {
    }

    public Item(Item item){
        sku = item.getSku();
        description = item.getDescription();
        cost = item.getCost();
        quantity = item.getQuantity();
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item{" +
                "sku='" + sku + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", quantity=" + quantity +
                '}';
    }
}
