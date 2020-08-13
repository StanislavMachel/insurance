package com.example.insurance.model;

import org.apache.commons.math3.util.Precision;

import javax.persistence.*;
import java.util.List;

@Entity
public class InsuranceCalcResult {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    private double annualFee;

    @ManyToMany
    List<ParameterRisk> parameterRisks;

    protected InsuranceCalcResult() {

    }

    public InsuranceCalcResult(Vehicle vehicle, double annualFee) {
        this.vehicle = vehicle;
        this.annualFee = annualFee;
    }

    public InsuranceCalcResult(Vehicle vehicle, List<ParameterRisk> parameterRisks) {
        this.vehicle = vehicle;
        this.parameterRisks = parameterRisks;
    }

    public double getMonthlyFee() {
        return Precision.round(this.annualFee / 12, 2);
    }

    public double getAnnualFee() {
        return annualFee;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setAnnualFee(double annualFee) {
        this.annualFee = annualFee;
    }

    public List<ParameterRisk> getParameterRisks() {
        return parameterRisks;
    }
}
