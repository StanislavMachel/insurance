package com.example.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {

    @Id
    private Long id;
    private String plateNumber;
    private int firstRegistration;
    private double purchasePrice;
    private String producer;
    private double mileage;
    private double previousIndemnity;

    protected Vehicle() {
    }

    public Vehicle(Long id, String plateNumber,
                   int firstRegistration,
                   double purchasePrice,
                   String producer,
                   double mileage,
                   double previousIndemnity) {
        this.id = id;
        this.plateNumber = plateNumber;
        this.firstRegistration = firstRegistration;
        this.purchasePrice = purchasePrice;
        this.producer = producer;
        this.mileage = mileage;
        this.previousIndemnity = previousIndemnity;
    }

    public Long getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public int getFirstRegistration() {
        return firstRegistration;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public String getProducer() {
        return producer;
    }

    public double getMileage() {
        return mileage;
    }

    public double getPreviousIndemnity() {
        return previousIndemnity;
    }
}
