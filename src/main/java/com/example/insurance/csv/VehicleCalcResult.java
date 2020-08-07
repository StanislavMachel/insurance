package com.example.insurance.csv;

import com.example.insurance.model.Vehicle;

public class VehicleCalcResult extends Vehicle {

    private double annualFee;

    public VehicleCalcResult(Long id,
                             String plateNumber,
                             int firstRegistration,
                             double purchasePrice,
                             String producer,
                             double mileage,
                             double previousIndemnity,
                             double annualFee) {
        super(id,
                plateNumber,
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
