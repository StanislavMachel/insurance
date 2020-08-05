package com.example.insurance.services;

import com.example.insurance.model.VehicleCalcResult;
import com.example.insurance.repositories.CoefficientRepository;
import com.example.insurance.repositories.VehicleRepository;
import com.example.insurance.utils.Calculator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InsuranceServiceImpl implements InsuranceService {

    private final VehicleRepository vehicleRepository;
    private final CoefficientRepository coefficientRepository;

    public InsuranceServiceImpl(VehicleRepository vehicleRepository, CoefficientRepository coefficientRepository) {
        this.vehicleRepository = vehicleRepository;
        this.coefficientRepository = coefficientRepository;
    }

    @Override
    public List<VehicleCalcResult> getCalculationResult() {
        ArrayList<VehicleCalcResult> results = new ArrayList<>();

        vehicleRepository.findAll().forEach(vehicle -> {
            if (coefficientRepository.getAvgPurchasePriceByCarProducer(vehicle.getProducer()) != null) {
                VehicleCalcResult result = new VehicleCalcResult(
                        vehicle.getPlateNumber(),
                        vehicle.getFirstRegistration(),
                        vehicle.getPurchasePrice(),
                        vehicle.getProducer(),
                        vehicle.getMileage(),
                        vehicle.getPreviousIndemnity(),
                        Calculator.calculateAnnualFee(
                                coefficientRepository.getRiskByCarProducer(vehicle.getProducer()),
                                vehicle.getPurchasePrice(),
                                coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF),
                                (LocalDate.now().getYear() - vehicle.getFirstRegistration()),
                                coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF)),
                        Calculator.calculateMonthlyFee(
                                coefficientRepository.getRiskByCarProducer(vehicle.getProducer()),
                                vehicle.getPurchasePrice(),
                                coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_VALUE_RISK_COEFF),
                                (LocalDate.now().getYear() - vehicle.getFirstRegistration()),
                                coefficientRepository.getRiskByParameter(CoefficientRepository.VEHICLE_AGE_RISK_COEFF)));
                results.add(result);
            }
        });

        return results;
    }
}
