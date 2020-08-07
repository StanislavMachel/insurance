package com.example.insurance.utils;

import java.util.Arrays;

public class Calculator {
    public static double calculateAnnualFee(double carProducerRiskCoeff, ParamValueCoefficient... paramValueCoefficients) {
        return carProducerRiskCoeff * Arrays.stream(paramValueCoefficients).map(paramValueCoefficient -> paramValueCoefficient.getParamValue() * paramValueCoefficient.getCoefficient()).reduce(0d, Double::sum);
    }
}
