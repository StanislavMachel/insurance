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
                             double annualFee,
                             double monthlyFee) {
        super(plateNumber,
                firstRegistration,
                purchasePrice,
                producer,
                mileage,
                previousIndemnity);
        this.annualFee = annualFee;
        this.monthlyFee = monthlyFee;
    }

    public double getMonthlyFee() {
        return monthlyFee;
    }

    public double getAnnualFee() {
        return annualFee;
    }
}
