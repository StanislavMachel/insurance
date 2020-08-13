package com.example.insurance.migration;

import com.example.insurance.model.AvgPurchasePrice;
import com.example.insurance.model.CarProducerRisk;
import com.example.insurance.model.ParameterRisk;
import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.csv.VehicleCsvRepository;
import com.example.insurance.repositories.jpa.*;
import com.example.insurance.repositories.json.CoefficientRepository;
import com.example.insurance.services.db.InsuranceService;
import com.example.insurance.services.db.strategy.CalculationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbMigration implements CommandLineRunner {

    private static Logger LOG = LoggerFactory.getLogger(DbMigration.class);

    private final VehicleCsvRepository vehicleCsvRepository;
    private final VehicleJpaRepository vehicleJpaRepository;
    private final InsuranceCalcResultRepository insuranceCalcResultRepository;
    private final CoefficientRepository coefficientRepository;
    private final CarProducerRiskRepository carProducerRiskRepository;
    private final ParameterRiskRepository parameterRiskRepository;
    private final AvgPurchasePriceRepository avgPurchasePriceRepository;
    private final InsuranceService insuranceService;
    private final CalculationStrategy calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;

    public DbMigration(VehicleCsvRepository vehicleCsvRepository,
                       VehicleJpaRepository vehicleJpaRepository,
                       InsuranceCalcResultRepository insuranceCalcResultRepository,
                       CoefficientRepository coefficientRepository,
                       CarProducerRiskRepository carProducerRiskRepository,
                       ParameterRiskRepository parameterRiskRepository,
                       AvgPurchasePriceRepository avgPurchasePriceRepository,
                       InsuranceService insuranceService,
                       @Qualifier("CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity")
                               CalculationStrategy calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity) {
        this.vehicleCsvRepository = vehicleCsvRepository;
        this.vehicleJpaRepository = vehicleJpaRepository;
        this.insuranceCalcResultRepository = insuranceCalcResultRepository;
        this.coefficientRepository = coefficientRepository;
        this.carProducerRiskRepository = carProducerRiskRepository;
        this.parameterRiskRepository = parameterRiskRepository;
        this.avgPurchasePriceRepository = avgPurchasePriceRepository;
        this.insuranceService = insuranceService;
        this.calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity = calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;
    }

    @Override
    public void run(String... args) {
        LOG.info("Start migration to memory database");

        List<Vehicle> vehicles = vehicleCsvRepository.findAll();

        vehicleJpaRepository.saveAll(vehicles);

        LOG.info("Migrated {} vehicles", vehicleJpaRepository.count());


        List<CarProducerRisk> carProducerRisks = coefficientRepository.getCarProducerRisks().entrySet().stream()
                .map(carProducerRisk -> new CarProducerRisk(carProducerRisk.getKey().toUpperCase(), carProducerRisk.getValue()))
                .collect(Collectors.toList());

        carProducerRiskRepository.saveAll(carProducerRisks);

        LOG.info("Migrated {} carProducerRisks", carProducerRiskRepository.count());

        List<ParameterRisk> parameterRisks = coefficientRepository.getParameterRisks().entrySet().stream()
                .map(parameterRisk -> new ParameterRisk(parameterRisk.getKey().toLowerCase(), parameterRisk.getValue())).collect(Collectors.toList());

        parameterRiskRepository.saveAll(parameterRisks);

        LOG.info("Migrated {} parameterRisks", parameterRiskRepository.count());

        List<AvgPurchasePrice> avgPurchasePrices = coefficientRepository.getAvgPurchasePrices().entrySet().stream()
                .map(avgPurchasePrice -> new AvgPurchasePrice(avgPurchasePrice.getKey().toUpperCase(), avgPurchasePrice.getValue()))
                .collect(Collectors.toList());

        avgPurchasePriceRepository.saveAll(avgPurchasePrices);

        LOG.info("Migrated {} avgPurchasePrices", avgPurchasePriceRepository.count());


        insuranceService.getCalculationResults();

        LOG.info("{}", insuranceCalcResultRepository.count());

        insuranceService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity);

        insuranceService.getCalculationResults();

        LOG.info("Migrated {} calc results", insuranceCalcResultRepository.count());


        LOG.info("Migration to memory database sucessfully done");
    }
}
