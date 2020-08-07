package com.example.insurance.services;

import com.example.insurance.csv.VehicleCalcResult;
import com.example.insurance.repositories.csv.VehicleCsvRepository;
import com.example.insurance.services.calculation.strategy.CalculationStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private final VehicleCsvRepository vehicleCsvRepository;
    private CalculationStrategy calculationStrategy;

    public InsuranceServiceImpl(VehicleCsvRepository vehicleCsvRepository) {
        this.vehicleCsvRepository = vehicleCsvRepository;
    }

    @Override
    public List<VehicleCalcResult> getCalculationResults() {
        ArrayList<VehicleCalcResult> results = new ArrayList<>();

        vehicleCsvRepository.findAll().forEach(vehicle -> {

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
