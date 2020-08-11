package com.example.insurance.services.db.strategy;

import com.example.insurance.model.ParameterRisk;

import java.util.List;

public interface CalculationStrategy {

    String VEHICLE_VALUE_RISK_COEFF = "vehicle_value";
    String VEHICLE_AGE_RISK_COEFF = "vehicle_age";
    String VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF = "previous_indemnity";

    List<ParameterRisk> getParameterRisks();
}
