package com.example.insurance.repositories.json;

import com.example.insurance.dto.Data;
import com.example.insurance.dto.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class CoefficientRepositoryImpl implements CoefficientRepository {

    private static Logger LOG = LoggerFactory.getLogger(CoefficientRepositoryImpl.class);
    private static final String DATA_JSON = "data.json";
    private static final double DEFAULT_CAR_PRODUCER_RISK = 1;

    private final Data data;

    public CoefficientRepositoryImpl() {
        data = getData();
    }

    @Override
    public double getRiskByCarProducer(String carProducer) {
        return getCarProducerRisks().getOrDefault(carProducer, DEFAULT_CAR_PRODUCER_RISK);
    }

    @Override
    public double getRiskByParameter(String parameter) {
        return getParameterRisks().getOrDefault(parameter, DEFAULT_CAR_PRODUCER_RISK);
    }

    @Override
    public Double getAvgPurchasePriceByCarProducer(String carProducer) {
        return getAvgPurchasePrices().get(carProducer);
    }

    @Override
    public Map<String, Double> getCarProducerRisks() {
        return Collections.unmodifiableMap(Optional.ofNullable(data)
                .map(Data::getMakeCoefficients)
                .orElse(new LinkedHashMap<>()));
    }

    @Override
    public Map<String, Double> getParameterRisks() {
        return Collections.unmodifiableMap(Optional.ofNullable(data)
                .map(Data::getCoefficients)
                .orElse(new LinkedHashMap<>()));
    }

    @Override
    public Map<String, Double> getAvgPurchasePrices() {
        return Collections.unmodifiableMap(Optional.ofNullable(data)
                .map(Data::getAvgPurchasePrice)
                .orElse(new LinkedHashMap<>()));
    }

    private Data getData() {
        return Optional.ofNullable(getClass().getClassLoader().getResource(DATA_JSON))
                .map(url -> new File(url.getFile()))
                .map(file -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        return objectMapper.readValue(file, Response.class);
                    } catch (IOException e) {
                        LOG.warn(e.getMessage());
                        return null;
                    }
                })
                .map(Response::getData).orElse(null);
    }
}
