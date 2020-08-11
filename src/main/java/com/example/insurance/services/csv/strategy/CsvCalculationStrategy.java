package com.example.insurance.services.csv.strategy;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;

public interface CsvCalculationStrategy {
    InsuranceCalcResult getVehicleCalcResult(Vehicle vehicle);
}
