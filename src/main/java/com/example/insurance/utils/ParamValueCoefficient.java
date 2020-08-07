package com.example.insurance.utils;

public class ParamValueCoefficient {
    private double paramValue;
    private double coefficient;

    public ParamValueCoefficient(double paramValue, double coefficient) {
        this.paramValue = paramValue;
        this.coefficient = coefficient;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public double getParamValue() {
        return paramValue;
    }
}
