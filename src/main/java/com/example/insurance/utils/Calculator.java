package com.example.insurance.utils;

import org.apache.commons.math3.util.Precision;

import java.util.Arrays;

public class Calculator {
    public static double calculateAnnualFee(double carProducerRiskCoeff, ParamValueCoefficient... paramValueCoefficients) {
        return Precision.round(carProducerRiskCoeff * Arrays.stream(paramValueCoefficients).map(paramValueCoefficient -> paramValueCoefficient.getParamValue() * paramValueCoefficient.getCoefficient()).reduce(0d, Double::sum), 2);
    }
}
