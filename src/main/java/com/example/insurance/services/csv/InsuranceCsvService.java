package com.example.insurance.services.csv;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.services.csv.strategy.CsvCalculationStrategy;

import java.util.List;

public interface InsuranceCsvService {
    List<InsuranceCalcResult> getCalculationResults();

    void setCalculationStrategy(CsvCalculationStrategy csvCalculationStrategy);
}
