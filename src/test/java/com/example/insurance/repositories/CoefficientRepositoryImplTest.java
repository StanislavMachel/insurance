package com.example.insurance.repositories;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class CoefficientRepositoryImplTest {

    private CoefficientRepository coefficientRepository;

    @Before
    public void setUp() {
        coefficientRepository = new CoefficientRepositoryImpl();
    }

    @Test
    public void getRiskByCarProducerKnown() throws IOException {
        Assert.assertEquals(0.95, coefficientRepository.getRiskByCarProducer("volvo"), 0.001);
        Assert.assertEquals(0.85, coefficientRepository.getRiskByCarProducer("tatra"), 0.001);
        Assert.assertEquals(1.2, coefficientRepository.getRiskByCarProducer("subaru"), 0.001);
        Assert.assertEquals(1.3, coefficientRepository.getRiskByCarProducer("bmw"), 0.001);
        Assert.assertEquals(1.2, coefficientRepository.getRiskByCarProducer("porsche"), 0.001);
        Assert.assertEquals(11, coefficientRepository.getRiskByCarProducer("tesla"), 0.001);
    }

    @Test
    public void getRiskByCarProducerUnknown() throws IOException {
        Assert.assertEquals(1, coefficientRepository.getRiskByCarProducer("unknown"), 0.001);
    }

    @Test
    public void getRiskByParameterKnown() throws IOException {
        Assert.assertEquals(1.1, coefficientRepository.getRiskByParameter("vehicle_age"), 0.001);
        Assert.assertEquals(0.08, coefficientRepository.getRiskByParameter("vehicle_value"), 0.001);
        Assert.assertEquals(0.05, coefficientRepository.getRiskByParameter("previous_indemnity"), 0.001);
    }

    @Test
    public void getRiskByParameterUnknown() throws IOException {
        Assert.assertEquals(1, coefficientRepository.getRiskByParameter("unknown"), 0.001);
    }

    @Test
    public void getAvgPurchasePriceByCarProducerKnown() throws IOException {
        Double porcheAvgPurchasePrice =  coefficientRepository.getAvgPurchasePriceByCarProducer("PORSCHE");
        Assert.assertNotNull(porcheAvgPurchasePrice);
        Assert.assertEquals(49687, porcheAvgPurchasePrice, 0.001);
    }

    @Test
    public void getAvgPurchasePriceByCarProducerUnknown() throws IOException {
        Assert.assertNull(coefficientRepository.getAvgPurchasePriceByCarProducer("UNKNOWN"));
    }
}