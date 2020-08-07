package com.example.insurance.repositories.json;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoefficientRepositoryImplTest {

    private CoefficientRepository coefficientRepository;

    @BeforeEach
    public void setUp() {
        coefficientRepository = new CoefficientRepositoryImpl();
    }

    @Test
    public void getRiskByCarProducerKnown() {
        assertEquals(0.95, coefficientRepository.getRiskByCarProducer("volvo"), 0.001);
        assertEquals(0.85, coefficientRepository.getRiskByCarProducer("tatra"), 0.001);
        assertEquals(1.2, coefficientRepository.getRiskByCarProducer("subaru"), 0.001);
        assertEquals(1.3, coefficientRepository.getRiskByCarProducer("bmw"), 0.001);
        assertEquals(1.2, coefficientRepository.getRiskByCarProducer("porsche"), 0.001);
        assertEquals(11, coefficientRepository.getRiskByCarProducer("tesla"), 0.001);
    }

    @Test
    public void getRiskByCarProducerUnknown() {
        assertEquals(1, coefficientRepository.getRiskByCarProducer("unknown"), 0.001);
    }

    @Test
    public void getRiskByParameterKnown() {
        assertEquals(1.1, coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF), 0.001);
        assertEquals(0.08, coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF), 0.001);
        assertEquals(0.05, coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF), 0.001);
    }

    @Test
    public void getRiskByParameterUnknown() {
        assertEquals(1, coefficientRepository.getRiskByParameter("unknown"), 0.001);
    }

    @Test
    public void getAvgPurchasePriceByCarProducerKnown() {
        Double porcheAvgPurchasePrice = coefficientRepository.getAvgPurchasePriceByCarProducer("PORSCHE");
        assertNotNull(porcheAvgPurchasePrice);
        assertEquals(49687, porcheAvgPurchasePrice, 0.001);
    }

    @Test
    public void getAvgPurchasePriceByCarProducerUnknown() {
        assertNull(coefficientRepository.getAvgPurchasePriceByCarProducer("UNKNOWN"));
    }
}