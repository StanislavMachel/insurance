package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.CoefficientRepository;
import com.example.insurance.utils.ParamValueCoefficient;

import java.time.LocalDate;

public abstract class CalculationStrategyBase implements CalculationStrategy {

    protected CoefficientRepository coefficientRepository;

    @Override
    public void setCoefficientRepository(CoefficientRepository coefficientRepository) {
        this.coefficientRepository = coefficientRepository;
    }

    protected double getRiskByCarProducerCoefficient(Vehicle vehicle) {
        return coefficientRepository.getRiskByCarProducer(vehicle.getProducer());
    }

    protected ParamValueCoefficient getVehicleValueParamValueCoefficient(Vehicle vehicle) {
        return new ParamValueCoefficient(vehicle.getPurchasePrice(), coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF));
    }

    protected ParamValueCoefficient getVehicleAgeParamValueCoefficient(Vehicle vehicle) {
        return new ParamValueCoefficient((LocalDate.now().getYear() - vehicle.getFirstRegistration()), coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF));
    }

    protected ParamValueCoefficient getVehiclePreviousIndemnityParamValueCoefficient(Vehicle vehicle) {
        return new ParamValueCoefficient(vehicle.getPreviousIndemnity(), coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF));
    }
}
