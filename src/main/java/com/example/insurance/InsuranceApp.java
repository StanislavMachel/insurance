package com.example.insurance;

import com.example.insurance.migration.DbMigration;
import com.example.insurance.services.InsuranceService;
import com.example.insurance.services.calculation.strategy.CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue;
import com.example.insurance.services.calculation.strategy.CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;
import com.example.insurance.utils.CsvFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InsuranceApp {

    private static Logger LOG = LoggerFactory.getLogger(InsuranceApp.class);

    private static final String RESULT_3_COEFF_CSV = "result_3_coeff.csv";
    private static final String RESULT_4_COEFF_CSV = "result_4_coeff.csv";


    public static void main(String[] args) {
        SpringApplication.run(InsuranceApp.class, args);
    }

    @Bean
    public CommandLineRunner run(InsuranceService insuranceService,
                                 CalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue,
                                 CalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity,
                                 DbMigration dbMigration) {
        return args -> {
            LOG.info("InsuranceApp start...");

            insuranceService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue);

            CsvFileUtils.writeFile(insuranceService.getCalculationResults(), RESULT_3_COEFF_CSV);

            insuranceService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity);

            CsvFileUtils.writeFile(insuranceService.getCalculationResults(), RESULT_4_COEFF_CSV);

            dbMigration.run();

            LOG.info("InsuranceApp ends.");

        };
    }
}
