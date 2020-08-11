package com.example.insurance.services.db;

import com.example.insurance.services.db.strategy.CalculationStrategy;

public interface InsuranceService {
    void getCalculationResults();

    void setCalculationStrategy(CalculationStrategy calculationStrategy);
}
