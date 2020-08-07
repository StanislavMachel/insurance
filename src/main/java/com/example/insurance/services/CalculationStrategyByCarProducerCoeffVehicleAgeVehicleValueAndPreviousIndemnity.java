package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.utils.Calculator;

public class CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity extends CalculationStrategyBase implements CalculationStrategy {
    @Override
    public double calculateAnnualFee(Vehicle vehicle) {
        return Calculator.calculateAnnualFee(getRiskByCarProducerCoefficient(vehicle), getVehicleValueParamValueCoefficient(vehicle), getVehicleAgeParamValueCoefficient(vehicle), getVehiclePreviousIndemnityParamValueCoefficient(vehicle));
    }
}
