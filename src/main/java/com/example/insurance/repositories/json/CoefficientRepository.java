package com.example.insurance.repositories.json;

import java.util.Map;

public interface CoefficientRepository {

    String VEHICLE_VALUE_RISK_COEFF = "vehicle_value";
    String VEHICLE_AGE_RISK_COEFF = "vehicle_age";
    String VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF = "previous_indemnity";

    double getRiskByCarProducer(String carProducer);

    double getRiskByParameter(String parameter);

    Double getAvgPurchasePriceByCarProducer(String carProducer);

    Map<String, Double> getCarProducerRisks();

    Map<String, Double> getParameterRisks();

    Map<String, Double> getAvgPurchasePrices();
}
