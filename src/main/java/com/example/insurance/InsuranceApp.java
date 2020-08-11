package com.example.insurance;

import com.example.insurance.migration.DbMigration;
import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.services.csv.InsuranceCsvService;
import com.example.insurance.services.csv.strategy.CsvCalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue;
import com.example.insurance.services.csv.strategy.CsvCalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity;
import com.example.insurance.utils.CsvFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class InsuranceApp {

    private static Logger LOG = LoggerFactory.getLogger(InsuranceApp.class);

    private static final String RESULT_3_COEFF_CSV = "result_3_coeff.csv";
    private static final String RESULT_4_COEFF_CSV = "result_4_coeff.csv";


    public static void main(String[] args) {
        SpringApplication.run(InsuranceApp.class, args);
    }

    @Bean
    public CommandLineRunner run(InsuranceCsvService insuranceCsvService,
                                 CsvCalculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue,
                                 CsvCalculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity,
                                 DbMigration dbMigration) {
        return args -> {
            LOG.info("InsuranceApp start...");

            insuranceCsvService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeAndVehicleValue);

            List<InsuranceCalcResult> results1 = insuranceCsvService.getCalculationResults();
            CsvFileUtils.writeFile(results1, RESULT_3_COEFF_CSV);

            insuranceCsvService.setCalculationStrategy(calculationStrategyByCarProducerCoeffVehicleAgeVehicleValueAndPreviousIndemnity);

            List<InsuranceCalcResult> results2 = insuranceCsvService.getCalculationResults();
            CsvFileUtils.writeFile(results2, RESULT_4_COEFF_CSV);

            //dbMigration.run();

            LOG.info("InsuranceApp ends.");

        };
    }
}
