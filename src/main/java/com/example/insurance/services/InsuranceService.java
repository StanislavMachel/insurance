package com.example.insurance.services;

import com.example.insurance.model.VehicleCalcResult;

import java.util.List;

public interface InsuranceService {
    List<VehicleCalcResult> getCalculationResult();
}
