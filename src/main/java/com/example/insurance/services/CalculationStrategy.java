package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.CoefficientRepository;

public interface CalculationStrategy {
    double calculateAnnualFee(Vehicle vehicle);
    void setCoefficientRepository(CoefficientRepository coefficientRepository);
}
