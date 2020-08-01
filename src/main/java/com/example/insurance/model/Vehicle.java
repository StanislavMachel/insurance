package com.example.insurance.model;

public class Vehicle {

  private String plateNumber;
  private short firstRegistration;
  private double purchasePrice;
  private String producer;
  private double mileage;
  private double previousIndemnity;

  public Vehicle(String plateNumber,
                 short firstRegistration,
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

  public void setPlateNumber(String plateNumber) {
    this.plateNumber = plateNumber;
  }

  public short getFirstRegistration() {
    return firstRegistration;
  }

  public void setFirstRegistration(short firstRegistration) {
    this.firstRegistration = firstRegistration;
  }

  public double getPurchasePrice() {
    return purchasePrice;
  }

  public void setPurchasePrice(double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  public String getProducer() {
    return producer;
  }

  public void setProducer(String producer) {
    this.producer = producer;
  }

  public double getMileage() {
    return mileage;
  }

  public void setMileage(double mileage) {
    this.mileage = mileage;
  }

  public double getPreviousIndemnity() {
    return previousIndemnity;
  }

  public void setPreviousIndemnity(double previousIndemnity) {
    this.previousIndemnity = previousIndemnity;
  }
}
