package com.example.insurance.services.calculation.strategy;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;

public interface CalculationStrategy {
    InsuranceCalcResult getVehicleCalcResult(Vehicle vehicle);
}
