package com.example.insurance.services.db.strategy;

import com.example.insurance.repositories.jpa.ParameterRiskRepository;

public abstract class CalculationStrategyBase implements CalculationStrategy {

    protected final ParameterRiskRepository parameterRiskRepository;

    protected CalculationStrategyBase(ParameterRiskRepository parameterRiskRepository){
        this.parameterRiskRepository = parameterRiskRepository;
    }
}
