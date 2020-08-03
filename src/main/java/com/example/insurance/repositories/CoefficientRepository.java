package com.example.insurance.repositories;

import java.io.IOException;

public interface CoefficientRepository {
    double getRiskByCarProducer(String carProducer) throws IOException;

    double getRiskByParameter(String parameter) throws IOException;

    Double getAvgPurchasePriceByCarProducer(String carProducer) throws IOException;
}
