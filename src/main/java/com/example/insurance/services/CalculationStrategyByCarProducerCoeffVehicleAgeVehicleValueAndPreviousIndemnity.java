package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.CoefficientRepository;
import com.example.insurance.utils.Calculator;

public class CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity extends CalculationStrategyBase implements CalculationStrategy {

    private CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity(CoefficientRepository coefficientRepository) {
        super(coefficientRepository);
    }

    @Override
    protected double calculateAnnualFee(Vehicle vehicle) {
        return Calculator.calculateAnnualFee(getRiskByCarProducerCoefficient(vehicle), getVehicleValueParamValueCoefficient(vehicle), getVehicleAgeParamValueCoefficient(vehicle), getVehiclePreviousIndemnityParamValueCoefficient(vehicle));
    }
}
