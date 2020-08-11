package com.example.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AvgPurchasePrice {
    @Id
    @GeneratedValue
    private Long id;
    private String carProducer;
    private double avgPurchase;

    protected AvgPurchasePrice() {

    }

    public AvgPurchasePrice(String carProducer, double avgPurchase) {
        this.carProducer = carProducer;
        this.avgPurchase = avgPurchase;
    }

    public String getCarProducer() {
        return carProducer;
    }

    public double getAvgPurchase() {
        return avgPurchase;
    }
}
