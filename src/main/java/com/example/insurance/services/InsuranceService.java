package com.example.insurance.services;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;
import com.example.insurance.services.calculation.strategy.CalculationStrategy;

import java.util.List;

public interface InsuranceService {
    List<InsuranceCalcResult> getCalculationResults(List<Vehicle> vehicles);

    void setCalculationStrategy(CalculationStrategy calculationStrategy);
}
