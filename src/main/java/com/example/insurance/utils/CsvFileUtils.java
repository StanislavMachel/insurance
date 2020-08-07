package com.example.insurance.utils;

import com.example.insurance.csv.VehicleCalcResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvFileUtils {


    private static Logger LOG = LoggerFactory.getLogger(CsvFileUtils.class);

    public static void writeFile(List<VehicleCalcResult> results, String destination) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destination))) {

            writer.write(getCsvFileHeaderRow());
            writer.newLine();

            for (VehicleCalcResult result : results) {
                writer.write(getCsvFileResultRow(result));
                writer.newLine();
            }
            LOG.info("Result wrote to: {}", destination);
        } catch (IOException e) {
            LOG.warn(e.getMessage());
        }
    }

    private static String getCsvFileHeaderRow() {
        return "plate_number,first_registration,purchase_price,producer,mileage,previous_indemnity,annual_fee,monthly_fee";
    }

    private static String getCsvFileResultRow(VehicleCalcResult result) {
        return String.format("%s,%s,%.2f,%s,%.2f,%.2f,%.2f,%.2f", result.getPlateNumber(), result.getFirstRegistration(),
                result.getPurchasePrice(), result.getProducer(), result.getMileage(), result.getPreviousIndemnity(),
                result.getAnnualFee(), result.getMonthlyFee());
    }
}
