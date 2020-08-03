package com.example.insurance.utils;

public class Calculator {
    public static double calculateAnnualFee(double price, double carProducerRiskCoeff, double vehicleAgeRiskCoeff, double vehicleAge) {
        return price * carProducerRiskCoeff * vehicleAgeRiskCoeff * vehicleAge / 100;
    }

    public static double calculateMonthlyFee(double price, double carProducerRiskCoeff, double vehicleAgeRiskCoeff, double vehicleAge) {
        return Calculator.calculateAnnualFee(price, carProducerRiskCoeff, vehicleAgeRiskCoeff, vehicleAge) / 12;
    }
}
