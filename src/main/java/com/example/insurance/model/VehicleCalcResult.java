package com.example.insurance.model;

public class VehicleCalcResult extends Vehicle {

    private double annualFee;
    private double monthlyFee;

    public VehicleCalcResult(String plateNumber,
                             int firstRegistration,
                             double purchasePrice,
                             String producer,
                             double mileage,
                             double previousIndemnity,
                             double annualFee) {
        super(plateNumber,
                firstRegistration,
                purchasePrice,
                producer,
                mileage,
                previousIndemnity);
        this.annualFee = annualFee;
    }

    public double getMonthlyFee() {
        return this.annualFee / 12;
    }

    public double getAnnualFee() {
        return annualFee;
    }
}
