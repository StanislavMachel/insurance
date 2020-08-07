package com.example.insurance.services;

import com.example.insurance.model.VehicleCalcResult;
import com.example.insurance.repositories.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

public class InsuranceServiceImpl implements InsuranceService {

    private final VehicleRepository vehicleRepository;
    private CalculationStrategy calculationStrategy;

    public InsuranceServiceImpl(VehicleRepository vehicleRepository, CalculationStrategy calculationStrategy) {
        this.vehicleRepository = vehicleRepository;
        this.calculationStrategy = calculationStrategy;
    }

    @Override
    public List<VehicleCalcResult> getCalculationResults() {
        ArrayList<VehicleCalcResult> results = new ArrayList<>();

        vehicleRepository.findAll().forEach(vehicle -> {

            VehicleCalcResult result = calculationStrategy.getVehicleCalcResult(vehicle);

            if (result != null) {
                results.add(result);
            }
        });

        return results;
    }

    @Override
    public void setCalculationStrategy(CalculationStrategy calculationStrategy) {
        this.calculationStrategy = calculationStrategy;
    }
}
