package com.example.insurance.services;

import com.example.insurance.model.Vehicle;
import com.example.insurance.model.VehicleCalcResult;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnityTest extends CalculationStrategyBaseTest {

    @InjectMocks
    CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity calculationStrategy;

    @Test
    void getVehicleCalcResult() {
        VehicleCalcResult result = calculationStrategy.getVehicleCalcResult(TEST_VEHICLE);

        checkVehicleCalcResult(result);

        double expectedAnnualFee = TEST_CAR_PRODUCER_RISK_COEFF * (TEST_VEHICLE_AGE_RISK_COEFF * (LocalDate.now().getYear() - TEST_VEHICLE_REGISTRATION) + TEST_VEHICLE_VALUE_RISK_COEFF * TEST_VEHICLE_PURCHASE_PRICE + TEST_VEHICLE_PREVIOUS_INDEMNITY * TEST_VEHICLE_PREVIOUS_INDEMNITY_RISK_COEFF);

        assertEquals(expectedAnnualFee, result.getAnnualFee());
        assertEquals(expectedAnnualFee / 12, result.getMonthlyFee());
    }

    @Test
    public void getVehicleCalcResultRerunsNullIfRiskByCarProducerNotExists() {
        Vehicle vehicle = new Vehicle(TEST_VEHICLE_PLATE_NUMBER, TEST_VEHICLE_REGISTRATION, TEST_VEHICLE_PURCHASE_PRICE, "UNKNOWN", TEST_VEHICLE_MILEAGE, TEST_VEHICLE_PREVIOUS_INDEMNITY);

        VehicleCalcResult result = calculationStrategy.getVehicleCalcResult(vehicle);

        assertNull(result);
    }
}