package com.example.insurance.services.csv.strategy;

import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.json.CoefficientRepository;
import com.example.insurance.utils.Calculator;
import org.springframework.stereotype.Component;

@Component
public class CsvCalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue extends CsvCalculationStrategyBase implements CsvCalculationStrategy {

    public CsvCalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue(CoefficientRepository coefficientRepository) {
        super(coefficientRepository);
    }

    @Override
    protected double calculateAnnualFee(Vehicle vehicle) {
        return Calculator.calculateAnnualFee(getRiskByCarProducerCoefficient(vehicle), getVehicleValueParamValueCoefficient(vehicle), getVehicleAgeParamValueCoefficient(vehicle));
    }
}
