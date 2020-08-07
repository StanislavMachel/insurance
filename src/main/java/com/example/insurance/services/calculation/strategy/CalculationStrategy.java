package com.example.insurance.services.calculation.strategy;

import com.example.insurance.model.Vehicle;
import com.example.insurance.model.VehicleCalcResult;

public interface CalculationStrategy {
    VehicleCalcResult getVehicleCalcResult(Vehicle vehicle);
}
