package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.model.VehicleCalcResult;
import com.example.insurance.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
    private static final Vehicle TEST_VEHICLE = new Vehicle(TEST_VEHICLE_PLATE_NUMBER, TEST_VEHICLE_REGISTRATION, TEST_VEHICLE_PURCHASE_PRICE, TEST_VEHICLE_PRODUCER, TEST_VEHICLE_MILEAGE, TEST_VEHICLE_PREVIOUS_INDEMNITY);

    @InjectMocks
    private InsuranceServiceImpl insuranceService;

    @Mock
    private VehicleRepository vehicleRepositoryMock;

    @Mock
    private CalculationStrategy calculationStrategy;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Mockito.when(vehicleRepositoryMock.findAll())
                .thenReturn(Arrays.asList(
                        TEST_VEHICLE,
                        new Vehicle("AAA001", 2010, 60000, "VOLVO", 20000, 10000)
                ));

        Mockito.when(calculationStrategy.getVehicleCalcResult(TEST_VEHICLE))
                .thenReturn(
                        new VehicleCalcResult(TEST_VEHICLE_PLATE_NUMBER,
                                TEST_VEHICLE_REGISTRATION,
                                TEST_VEHICLE_PURCHASE_PRICE,
                                TEST_VEHICLE_PRODUCER,
                                TEST_VEHICLE_MILEAGE,
                                TEST_VEHICLE_PREVIOUS_INDEMNITY,
                                0));
    }

    @Test
    public void getCalculationResults() {
        List<VehicleCalcResult> results = insuranceService.getCalculationResults();

        Mockito.verify(calculationStrategy, Mockito.times(2)).getVehicleCalcResult(Mockito.any());

        assertNotNull(results);
        assertEquals(1, results.size());
    }
}