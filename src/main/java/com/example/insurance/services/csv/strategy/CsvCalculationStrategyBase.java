package com.example.insurance.services.csv.strategy;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.json.CoefficientRepository;
import com.example.insurance.utils.ParamValueCoefficient;

import java.time.LocalDate;

public abstract class CsvCalculationStrategyBase implements CsvCalculationStrategy {

    protected final CoefficientRepository coefficientRepository;

    protected CsvCalculationStrategyBase(CoefficientRepository coefficientRepository) {
        this.coefficientRepository = coefficientRepository;
    }

    abstract protected double calculateAnnualFee(Vehicle vehicle);

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

    public InsuranceCalcResult getVehicleCalcResult(Vehicle vehicle) {
        if (coefficientRepository.getAvgPurchasePriceByCarProducer(vehicle.getProducer()) == null) return null;

        return new InsuranceCalcResult(vehicle, calculateAnnualFee(vehicle));
    }
}
