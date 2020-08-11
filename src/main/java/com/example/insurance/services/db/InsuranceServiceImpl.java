package com.example.insurance.services.db;

import com.example.insurance.model.CarProducerRisk;
import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.ParameterRisk;
import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.jpa.AvgPurchasePriceRepository;
import com.example.insurance.repositories.jpa.CarProducerRiskRepository;
import com.example.insurance.repositories.jpa.InsuranceCalcResultRepository;
import com.example.insurance.repositories.jpa.VehicleJpaRepository;
import com.example.insurance.services.db.strategy.CalculationStrategy;
import com.example.insurance.utils.Calculator;
import com.example.insurance.utils.ParamValueCoefficient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsuranceServiceImpl implements InsuranceService {

    private static Logger LOG = LoggerFactory.getLogger(InsuranceServiceImpl.class);


    private final String VEHICLE_VALUE_RISK_COEFF = "vehicle_value";
    private final String VEHICLE_AGE_RISK_COEFF = "vehicle_age";
    private final String VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF = "previous_indemnity";

    private final VehicleJpaRepository vehicleJpaRepository;
    private final CarProducerRiskRepository carProducerRiskRepository;
    private final AvgPurchasePriceRepository avgPurchasePriceRepository;
    private final InsuranceCalcResultRepository insuranceCalcResultRepository;
    private CalculationStrategy calculationStrategy;

    public InsuranceServiceImpl(VehicleJpaRepository vehicleJpaRepository,
                                CarProducerRiskRepository carProducerRiskRepository,
                                AvgPurchasePriceRepository avgPurchasePriceRepository,
                                InsuranceCalcResultRepository insuranceCalcResultRepository,
                                CalculationStrategy calculationStrategy) {
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.carProducerRiskRepository = carProducerRiskRepository;
        this.avgPurchasePriceRepository = avgPurchasePriceRepository;
        this.insuranceCalcResultRepository = insuranceCalcResultRepository;
        this.calculationStrategy = calculationStrategy;
    }


    @Override
    public void setCalculationStrategy(CalculationStrategy calculationStrategy) {
        this.calculationStrategy = calculationStrategy;
    }


    @Override
    public void getCalculationResults() {

        List<InsuranceCalcResult> results = vehicleJpaRepository.findAll().stream()
                .filter(v -> avgPurchasePriceRepository.findByCarProducer(v.getProducer().toUpperCase()) != null)
                .map(vehicle -> {
                    List<ParameterRisk> parameterRisks = calculationStrategy.getParameterRisks();

                    CarProducerRisk carProducerRisk = carProducerRiskRepository.findByCarProducer(vehicle.getProducer().toUpperCase());

                    double carProducerRiskValue = carProducerRisk == null ? 1 : carProducerRisk.getCoefficient();

                    InsuranceCalcResult result = new InsuranceCalcResult(vehicle, parameterRisks);

                    result.setAnnualFee(Calculator.calculateAnnualFee(carProducerRiskValue, getValueParamValueCoefficient(vehicle, parameterRisks)));

                    return result;

                }).collect(Collectors.toList());

        insuranceCalcResultRepository.saveAll(results);

        LOG.info("Count: {}", insuranceCalcResultRepository.count());

    }

    private ParamValueCoefficient getValueParamValueCoefficient(Vehicle vehicle, ParameterRisk parameterRisk) {
        double coefficient = parameterRisk.getCoefficient();
        double paramValue;
        switch (parameterRisk.getParameterName()) {
            case VEHICLE_VALUE_RISK_COEFF:
                paramValue = vehicle.getPurchasePrice();
                break;
            case VEHICLE_AGE_RISK_COEFF:
                paramValue = LocalDate.now().getYear() - vehicle.getFirstRegistration();
                break;
            case VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF:
                paramValue = vehicle.getPreviousIndemnity();
                break;
            default:
                throw new NotImplementedException();
        }

        return new ParamValueCoefficient(paramValue, coefficient);
    }

    private ParamValueCoefficient[] getValueParamValueCoefficient(Vehicle vehicle, List<ParameterRisk> parameterRisks) {
        ParamValueCoefficient[] paramValueCoefficients = new ParamValueCoefficient[parameterRisks.size()];

        for (int i = 0; i < parameterRisks.size(); i++) {
            paramValueCoefficients[i] = getValueParamValueCoefficient(vehicle, parameterRisks.get(i));
        }

        return paramValueCoefficients;
    }


}
