package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.model.VehicleCalcResult;
import com.example.insurance.repositories.CoefficientRepository;
import com.example.insurance.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class InsuranceServiceImplTest {

    private static final double TEST_CAR_PRODUCER_RISK_COEFF = 1.3;
    private static final double TEST_VEHICLE_AGE_RISK_COEFF = 1.1;
    private static final double TEST_VEHICLE_VALUE_RISK_COEFF = 0.08;
    private static final double TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF = 0.05;

    private static final String TEST_VEHICLE_PLATE_NUMBER = "AAA000";
    private static final int TEST_VEHICLE_REGISTRATION = 2019;
    private static final double TEST_VEHICLE_PURCHASE_PRICE = 60000;
    private static final String TEST_VEHICLE_PRODUCER = "BMW";
    private static final double TEST_VEHICLE_MILEAGE = 1000;
    private static final double TEST_VEHICLE_PREVIOUS_INDEMNITY = 5000;

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @Mock
    private VehicleRepository vehicleRepositoryMock;

    @Mock
    private CoefficientRepository coefficientRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(vehicleRepositoryMock.findAll())
                .thenReturn(Arrays.asList(
                        new Vehicle(TEST_VEHICLE_PLATE_NUMBER, TEST_VEHICLE_REGISTRATION, TEST_VEHICLE_PURCHASE_PRICE, TEST_VEHICLE_PRODUCER, TEST_VEHICLE_MILEAGE, TEST_VEHICLE_PREVIOUS_INDEMNITY),
                        new Vehicle("AAA001", 2010, 60000, "BMW", 20000, 10000),
                        new Vehicle("AAA002", 2015, 60000, "VOLVO", 20000, 10000)
                ));

        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer(Mockito.any())).thenReturn(1d);
        Mockito.when(coefficientRepository.getRiskByCarProducer(Mockito.any())).thenReturn(1d);
        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer(TEST_VEHICLE_PRODUCER)).thenReturn(45000d);
        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer("VOLVO")).thenReturn(null);
        Mockito.when(coefficientRepository.getRiskByCarProducer(TEST_VEHICLE_PRODUCER)).thenReturn(TEST_CAR_PRODUCER_RISK_COEFF);

        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF)).thenReturn(TEST_VEHICLE_AGE_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF)).thenReturn(TEST_VEHICLE_VALUE_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF)).thenReturn(TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF);
    }

    @Test
    public void getCalculationResultByCarProducerCoeffVehicleAgeAndVehicleValue() {
        List<VehicleCalcResult> results = insuranceService.getCalculationResultByCarProducerCoeffVehicleAgeAndVehicleValue();

        assertNotNull(results);
        assertEquals(2, results.size());
        VehicleCalcResult firstResult = results.get(0);
        assertNotNull(firstResult);
        assertEquals(TEST_VEHICLE_PLATE_NUMBER, firstResult.getPlateNumber());
        assertEquals(TEST_VEHICLE_REGISTRATION, firstResult.getFirstRegistration());
        assertEquals(TEST_VEHICLE_PURCHASE_PRICE, firstResult.getPurchasePrice());
        assertEquals(TEST_VEHICLE_PRODUCER, firstResult.getProducer());
        assertEquals(TEST_VEHICLE_MILEAGE, firstResult.getMileage());
        assertEquals(TEST_VEHICLE_PREVIOUS_INDEMNITY, firstResult.getPreviousIndemnity());

        double expectedAnnualFee = TEST_CAR_PRODUCER_RISK_COEFF * (TEST_VEHICLE_AGE_RISK_COEFF * (LocalDate.now().getYear() - TEST_VEHICLE_REGISTRATION) + TEST_VEHICLE_VALUE_RISK_COEFF * TEST_VEHICLE_PURCHASE_PRICE);
        double expectedMothlyFee = expectedAnnualFee / 12;

        assertEquals(expectedAnnualFee, firstResult.getAnnualFee());
        assertEquals(expectedMothlyFee, firstResult.getMonthlyFee());
    }

    @Test
    void getCalculationResultByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity() {
        List<VehicleCalcResult> results = insuranceService.getCalculationResultByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity();

        assertNotNull(results);
        assertEquals(2, results.size());
        VehicleCalcResult firstResult = results.get(0);
        assertNotNull(firstResult);
        assertEquals(TEST_VEHICLE_PLATE_NUMBER, firstResult.getPlateNumber());
        assertEquals(TEST_VEHICLE_REGISTRATION, firstResult.getFirstRegistration());
        assertEquals(TEST_VEHICLE_PURCHASE_PRICE, firstResult.getPurchasePrice());
        assertEquals(TEST_VEHICLE_PRODUCER, firstResult.getProducer());
        assertEquals(TEST_VEHICLE_MILEAGE, firstResult.getMileage());
        assertEquals(TEST_VEHICLE_PREVIOUS_INDEMNITY, firstResult.getPreviousIndemnity());

        double expectedAnnualFee = TEST_CAR_PRODUCER_RISK_COEFF * (TEST_VEHICLE_AGE_RISK_COEFF * (LocalDate.now().getYear() - TEST_VEHICLE_REGISTRATION) + TEST_VEHICLE_VALUE_RISK_COEFF * TEST_VEHICLE_PURCHASE_PRICE + TEST_VEHICLE_PREVIOUS_INDEMNITY * TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF);
        double expectedMothlyFee = expectedAnnualFee / 12;

        assertEquals(expectedAnnualFee, firstResult.getAnnualFee());
        assertEquals(expectedMothlyFee, firstResult.getMonthlyFee());
    }
}