package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.model.VehicleCalcResult;

public interface CalculationStrategy {
    VehicleCalcResult getVehicleCalcResult(Vehicle vehicle);
}
