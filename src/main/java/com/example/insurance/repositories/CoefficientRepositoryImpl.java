package com.example.insurance.repositories;

import com.example.insurance.dto.Data;
import com.example.insurance.dto.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class CoefficientRepositoryImpl implements CoefficientRepository {

    private static final Logger LOG = Logger.getLogger(VehicleRepositoryImpl.class.getName());
    private static final String DATA_JSON = "data.json";
    private static final double DEFAULT_CAR_PRODUCER_RISK = 1;

    private final Data data;

    public CoefficientRepositoryImpl() {
        data = getData();
    }

    @Override
    public double getRiskByCarProducer(String carProducer) {
        return Optional.ofNullable(data)
                .map(Data::getMakeCoefficients)
                .map(makeCoefficients -> makeCoefficients.get(carProducer))
                .orElse(DEFAULT_CAR_PRODUCER_RISK);
    }

    @Override
    public double getRiskByParameter(String parameter) {
        return Optional.ofNullable(data)
                .map(Data::getCoefficients)
                .map(makeCoefficients -> makeCoefficients.get(parameter))
                .orElse(DEFAULT_CAR_PRODUCER_RISK);
    }

    @Override
    public Double getAvgPurchasePriceByCarProducer(String carProducer) {
        return Optional.ofNullable(data)
                .map(Data::getAvgPurchasePrice)
                .map(avgPurchasePrices -> avgPurchasePrices.get(carProducer))
                .orElse(null);
    }

    private Data getData() {
        return Optional.ofNullable(getClass().getClassLoader().getResource(DATA_JSON))
                .map(url -> new File(url.getFile()))
                .map(file -> {
                    ObjectMapper objectMapper = new ObjectMapper();
                    try {
                        return objectMapper.readValue(file, Response.class);
                    } catch (IOException e) {
                        LOG.warning(e.getMessage());
                        return null;
                    }
                })
                .map(Response::getData).orElse(null);
    }
}
