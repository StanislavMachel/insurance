package com.example.insurance.services;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;
import com.example.insurance.services.calculation.strategy.CalculationStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private CalculationStrategy calculationStrategy;

    public InsuranceServiceImpl() {
    }

    @Override
    public List<InsuranceCalcResult> getCalculationResults(List<Vehicle> vehicles) {
        ArrayList<InsuranceCalcResult> results = new ArrayList<>();

        vehicles.forEach(vehicle -> {

            InsuranceCalcResult result = calculationStrategy.getVehicleCalcResult(vehicle);

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
