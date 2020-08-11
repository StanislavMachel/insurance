package com.example.insurance.services.db.strategy;

import com.example.insurance.model.ParameterRisk;
import com.example.insurance.repositories.jpa.ParameterRiskRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Primary
@Component("CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue")
public class CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue extends com.example.insurance.services.db.strategy.CalculationStrategyBase implements com.example.insurance.services.db.strategy.CalculationStrategy {

    protected CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue(ParameterRiskRepository parameterRiskRepository) {
        super(parameterRiskRepository);
    }


    @Override
    public List<ParameterRisk> getParameterRisks() {
        return parameterRiskRepository.findAllByParameterNameIn(Arrays.asList(VEHICLE_VALUE_RISK_COEFF, VEHICLE_AGE_RISK_COEFF));
    }
}
