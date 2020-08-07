package com.example.insurance.services.calculation.strategy;

import com.example.insurance.csv.VehicleCalcResult;
import com.example.insurance.model.Vehicle;

public interface CalculationStrategy {
    VehicleCalcResult getVehicleCalcResult(Vehicle vehicle);
}
