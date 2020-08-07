package com.example.insurance;

import com.example.insurance.repositories.CoefficientRepository;
import com.example.insurance.repositories.CoefficientRepositoryImpl;
import com.example.insurance.repositories.VehicleRepositoryImpl;
import com.example.insurance.services.CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue;
import com.example.insurance.services.InsuranceService;
import com.example.insurance.services.InsuranceServiceImpl;
import com.example.insurance.utils.CsvFileUtils;

public class InsuranceApp {

    private static final String RESULT_3_COEFF_CSV = "result_3_coeff.csv";

    public static void main(String[] args) {

        System.out.println("InsuranceApp start...");

        CoefficientRepository coefficientRepository = new CoefficientRepositoryImpl();

        InsuranceService insuranceService = new InsuranceServiceImpl(new VehicleRepositoryImpl(), new CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue(coefficientRepository));

        CsvFileUtils.writeFile(insuranceService.getCalculationResults(), RESULT_3_COEFF_CSV);

        System.out.println("InsuranceApp ends.");
    }
}
