package com.example.insurance.services;

import com.example.insurance.model.VehicleCalcResult;
import com.example.insurance.services.calculation.strategy.CalculationStrategy;

import java.util.List;

public interface InsuranceService {
    List<VehicleCalcResult> getCalculationResults();

    void setCalculationStrategy(CalculationStrategy calculationStrategy);
}
