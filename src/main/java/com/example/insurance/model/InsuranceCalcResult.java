package com.example.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class InsuranceCalcResult {

    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    private double annualFee;

    protected InsuranceCalcResult() {

    }

    public InsuranceCalcResult(Vehicle vehicle, double annualFee) {
        this.vehicle = vehicle;
        this.annualFee = annualFee;
    }

    public double getMonthlyFee() {
        return this.annualFee / 12;
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}
