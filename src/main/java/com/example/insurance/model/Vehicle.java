package com.example.insurance.model;

public class Vehicle {

    private String plateNumber;
    private int firstRegistration;
    private double purchasePrice;
    private String producer;
    private double mileage;
    private double previousIndemnity;

    public Vehicle(String plateNumber,
                   int firstRegistration,
                   double purchasePrice,
                   String producer,
                   double mileage,
                   double previousIndemnity) {
        this.plateNumber = plateNumber;
        this.firstRegistration = firstRegistration;
        this.purchasePrice = purchasePrice;
        this.producer = producer;
        this.mileage = mileage;
        this.previousIndemnity = previousIndemnity;
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
