package com.lessonsbyleo.practicerepo.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TotalBill {
    @JsonProperty
    private String id;
    @JsonProperty
    private double salesTax;
    @JsonProperty
    private double total;
    @JsonProperty
    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TotalBill{" +
                "id='" + id + '\'' +
                ", salesTax=" + salesTax +
                ", total=" + total +
                ", order=" + order +
                '}';
    }
}
