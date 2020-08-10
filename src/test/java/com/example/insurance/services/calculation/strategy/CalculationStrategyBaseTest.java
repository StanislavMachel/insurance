package com.example.insurance.services.calculation.strategy;

import com.example.insurance.model.Vehicle;
import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.repositories.json.CoefficientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculationStrategyBaseTest {
    protected static final long TEST_VEHICLE_ID = 1;
    protected static final String TEST_VEHICLE_PLATE_NUMBER = "AAA000";
    protected static final int TEST_VEHICLE_REGISTRATION = 2019;
    protected static final double TEST_VEHICLE_PURCHASE_PRICE = 60000;
    protected static final String TEST_VEHICLE_PRODUCER = "BMW";
    protected static final double TEST_VEHICLE_MILEAGE = 1000;
    protected static final double TEST_VEHICLE_PREVIOUS_INDEMNITY = 5000;
    protected static final double TEST_CAR_PRODUCER_RISK_COEFF = 1.3;
    protected static final double TEST_VEHICLE_AGE_RISK_COEFF = 1.1;
    protected static final double TEST_VEHICLE_VALUE_RISK_COEFF = 0.08;
    protected static final double TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF = 0.05;
    protected static final Vehicle TEST_VEHICLE = new Vehicle(TEST_VEHICLE_ID, TEST_VEHICLE_PLATE_NUMBER, TEST_VEHICLE_REGISTRATION, TEST_VEHICLE_PURCHASE_PRICE, TEST_VEHICLE_PRODUCER, TEST_VEHICLE_MILEAGE, TEST_VEHICLE_PREVIOUS_INDEMNITY);

    protected static final String UNKNOWN_VEHICLE_PRODUCER = "UNKNOWN";
    @Mock
    protected CoefficientRepository coefficientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer(UNKNOWN_VEHICLE_PRODUCER)).thenReturn(null);
        Mockito.when(coefficientRepository.getRiskByCarProducer(TEST_VEHICLE_PRODUCER)).thenReturn(TEST_CAR_PRODUCER_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF)).thenReturn(TEST_VEHICLE_AGE_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF)).thenReturn(TEST_VEHICLE_VALUE_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF)).thenReturn(TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF);
    }

    protected void checkVehicleCalcResult(InsuranceCalcResult result) {
        assertNotNull(result);
        assertNotNull(result.getVehicle());
        assertEquals(TEST_VEHICLE_PLATE_NUMBER, result.getVehicle().getPlateNumber());
        assertEquals(TEST_VEHICLE_REGISTRATION, result.getVehicle().getFirstRegistration());
        assertEquals(TEST_VEHICLE_PURCHASE_PRICE, result.getVehicle().getPurchasePrice());
        assertEquals(TEST_VEHICLE_PRODUCER, result.getVehicle().getProducer());
        assertEquals(TEST_VEHICLE_MILEAGE, result.getVehicle().getMileage());
        assertEquals(TEST_VEHICLE_PREVIOUS_INDEMNITY, result.getVehicle().getPreviousIndemnity());
    }


}
