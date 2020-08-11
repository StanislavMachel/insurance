package com.example.insurance.services.csv;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.repositories.csv.VehicleCsvRepository;
import com.example.insurance.services.csv.strategy.CsvCalculationStrategy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InsuranceCsvServiceImpl implements InsuranceCsvService {

    private final VehicleCsvRepository vehicleCsvRepository;
    private CsvCalculationStrategy csvCalculationStrategy;

    public InsuranceCsvServiceImpl(VehicleCsvRepository vehicleCsvRepository) {
        this.vehicleCsvRepository = vehicleCsvRepository;
    }

    @Override
    public List<InsuranceCalcResult> getCalculationResults() {
        ArrayList<InsuranceCalcResult> results = new ArrayList<>();

        vehicleCsvRepository.findAll().forEach(vehicle -> {

            InsuranceCalcResult result = csvCalculationStrategy.getVehicleCalcResult(vehicle);

            if (result != null) {
                results.add(result);
            }
        });

        return results;
    }

    @Override
    public void setCalculationStrategy(CsvCalculationStrategy csvCalculationStrategy) {
        this.csvCalculationStrategy = csvCalculationStrategy;
    }
}
