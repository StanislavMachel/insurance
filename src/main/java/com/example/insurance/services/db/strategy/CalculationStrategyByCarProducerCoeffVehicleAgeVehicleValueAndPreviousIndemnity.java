package com.example.insurance.services.db.strategy;

import com.example.insurance.model.ParameterRisk;
import com.example.insurance.repositories.jpa.ParameterRiskRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component("CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity")
public class CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity extends com.example.insurance.services.db.strategy.CalculationStrategyBase implements com.example.insurance.services.db.strategy.CalculationStrategy {
    protected CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity(ParameterRiskRepository parameterRiskRepository) {
        super(parameterRiskRepository);
    }

    @Override
    public List<ParameterRisk> getParameterRisks() {
        return parameterRiskRepository.findAllByParameterNameIn(Arrays.asList(VEHICLE_VALUE_RISK_COEFF, VEHICLE_AGE_RISK_COEFF, VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF));
    }
}
