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

import java.io.IOException;
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

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @Mock
    private VehicleRepository vehicleRepositoryMock;

    @Mock
    private CoefficientRepository coefficientRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCalculationResult() {

        String firstVehiclePlateNumber = "AAA000";
        int firstVehicleRegistration = 2019;
        double firstVehiclePurchasePrice = 60000;
        String firstVehicleProducer = "BMW";
        double firstVehicleMileage = 1000;
        double firstVehiclePreviousIndemnity = 5000;
        Mockito.when(vehicleRepositoryMock.findAll())
                .thenReturn(Arrays.asList(
                        new Vehicle(firstVehiclePlateNumber, firstVehicleRegistration, firstVehiclePurchasePrice, firstVehicleProducer, firstVehicleMileage, firstVehiclePreviousIndemnity),
                        new Vehicle("AAA001", 2010, 60000, "BMW", 20000, 10000),
                        new Vehicle("AAA002", 2015, 60000, "VOLVO", 20000, 10000)
                ));

        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer(Mockito.any())).thenReturn(1d);
        Mockito.when(coefficientRepository.getRiskByCarProducer(Mockito.any())).thenReturn(1d);
        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer(firstVehicleProducer)).thenReturn(45000d);
        Mockito.when(coefficientRepository.getAvgPurchasePriceByCarProducer("VOLVO")).thenReturn(null);
        Mockito.when(coefficientRepository.getRiskByCarProducer(firstVehicleProducer)).thenReturn(TEST_CAR_PRODUCER_RISK_COEFF);

        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF)).thenReturn(TEST_VEHICLE_AGE_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF)).thenReturn(TEST_VEHICLE_VALUE_RISK_COEFF);
        Mockito.when(coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF)).thenReturn(TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF);


        List<VehicleCalcResult> results = insuranceService.getCalculationResult();

        assertNotNull(results);
        assertEquals(2, results.size());
        VehicleCalcResult firstResult = results.get(0);
        assertNotNull(firstResult);
        assertEquals(firstVehiclePlateNumber, firstResult.getPlateNumber());
        assertEquals(firstVehicleRegistration, firstResult.getFirstRegistration());
        assertEquals(firstVehiclePurchasePrice, firstResult.getPurchasePrice());
        assertEquals(firstVehicleProducer, firstResult.getProducer());
        assertEquals(firstVehicleMileage, firstResult.getMileage());
        assertEquals(firstVehiclePreviousIndemnity, firstResult.getPreviousIndemnity());

        double expectedAnnualFee = TEST_CAR_PRODUCER_RISK_COEFF * (TEST_VEHICLE_AGE_RISK_COEFF * (LocalDate.now().getYear() - firstVehicleRegistration) + TEST_VEHICLE_VALUE_RISK_COEFF * firstVehiclePurchasePrice);
        double expectedMothlyFee = expectedAnnualFee / 12;

        assertEquals(expectedAnnualFee, firstResult.getAnnualFee());
        assertEquals(expectedMothlyFee, firstResult.getMonthlyFee());
    }
}