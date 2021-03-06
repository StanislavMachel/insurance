package com.example.insurance.services.csv.strategy;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CsvCalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValueTest extends CsvCalculationStrategyBaseTest {

    @InjectMocks
    CsvCalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue calculationStrategy;

    @Test
    void getVehicleCalcResult() {
        InsuranceCalcResult result = calculationStrategy.getVehicleCalcResult(TEST_VEHICLE);

        checkVehicleCalcResult(result);

        double expectedAnnualFee = TEST_CAR_PRODUCER_RISK_COEFF * (TEST_VEHICLE_AGE_RISK_COEFF * (LocalDate.now().getYear() - TEST_VEHICLE_REGISTRATION) + TEST_VEHICLE_VALUE_RISK_COEFF * TEST_VEHICLE_PURCHASE_PRICE);

        assertEquals(expectedAnnualFee, result.getAnnualFee());
        assertEquals(expectedAnnualFee / 12, result.getMonthlyFee(), 0.01);
    }

    @Test
    public void getVehicleCalcResultRerunsNullIfRiskByCarProducerNotExists() {
        Vehicle vehicle = new Vehicle(TEST_VEHICLE_ID, TEST_VEHICLE_PLATE_NUMBER, TEST_VEHICLE_REGISTRATION, TEST_VEHICLE_PURCHASE_PRICE, "UNKNOWN", TEST_VEHICLE_MILEAGE, TEST_VEHICLE_PREVIOUS_INDEMNITY);

        InsuranceCalcResult result = calculationStrategy.getVehicleCalcResult(vehicle);

        assertNull(result);
    }

}