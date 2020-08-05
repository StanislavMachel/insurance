package com.example.insurance.utils;

public class Calculator {
    public static double calculateAnnualFee(double carProducerRiskCoeff,
                                            double vehicleValue,
                                            double vehicleValueRiskCoeff,
                                            double vehicleAge,
                                            double vehicleAgeRiskCoeff) {
        return carProducerRiskCoeff * (vehicleValue * vehicleValueRiskCoeff + vehicleAge * vehicleAgeRiskCoeff);
    }

    public static double calculateMonthlyFee(double carProducerRiskCoeff,
                                             double vehicleValue,
                                             double vehicleValueRiskCoeff,
                                             double vehicleAge,
                                             double vehicleAgeRiskCoeff) {
        return Calculator.calculateAnnualFee(carProducerRiskCoeff, vehicleValue, vehicleValueRiskCoeff, vehicleAge, vehicleAgeRiskCoeff) / 12;
    }
}
