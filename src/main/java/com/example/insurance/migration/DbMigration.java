package com.example.insurance.migration;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.csv.VehicleCsvRepository;
import com.example.insurance.repositories.jpa.InsuranceCalcResultRepository;
import com.example.insurance.repositories.jpa.VehicleRepository;
import com.example.insurance.services.InsuranceService;
import com.example.insurance.services.calculation.strategy.CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue;
import com.example.insurance.services.calculation.strategy.CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbMigration {

    private static Logger LOG = LoggerFactory.getLogger(DbMigration.class);

    private final VehicleCsvRepository vehicleCsvRepository;
    private final VehicleRepository vehicleRepository;
    private final InsuranceCalcResultRepository insuranceCalcResultRepository;
    private final InsuranceService insuranceService;
    private final CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue;
    private final CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;

    public DbMigration(VehicleCsvRepository vehicleCsvRepository,
                       VehicleRepository vehicleRepository,
                       InsuranceCalcResultRepository insuranceCalcResultRepository, InsuranceService insuranceService,
                       CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue,
                       CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity) {
        this.vehicleCsvRepository = vehicleCsvRepository;
        this.vehicleRepository = vehicleRepository;
        this.insuranceCalcResultRepository = insuranceCalcResultRepository;
        this.insuranceService = insuranceService;
        this.calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue = calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue;
        this.calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity = calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;
    }

    public void run() {
        LOG.info("Start migration to memory database");

        List<Vehicle> vehicles = vehicleCsvRepository.findAll();

        vehicleRepository.saveAll(vehicles);

        insuranceService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue);

        List<InsuranceCalcResult> calcResults1 = insuranceService.getCalculationResults(vehicles);

        insuranceService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity);

        List<InsuranceCalcResult> calcResults2 = insuranceService.getCalculationResults(vehicles);

        insuranceCalcResultRepository.saveAll(calcResults1);
        insuranceCalcResultRepository.saveAll(calcResults2);

        LOG.info("Migrated {} vehicles", vehicleRepository.count());
        LOG.info("Migrated {} calc results", insuranceCalcResultRepository.count());
        LOG.info("Migration to memory database ends...");

    }
}
