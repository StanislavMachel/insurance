package com.example.insurance.repositories.csv;

import com.example.insurance.model.Vehicle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Repository
public class VehicleCsvRepositoryImpl implements VehicleCsvRepository {

    private static final String VEHICLES_CSV = "vehicles.csv";
    private static Logger LOG = LoggerFactory.getLogger(VehicleCsvRepositoryImpl.class);

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> results = new ArrayList<>();


        URL resource = getClass().getClassLoader().getResource(VEHICLES_CSV);

        if (resource == null) return null;

        File vehiclesCsvFile = new File(resource.getFile());

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader(vehiclesCsvFile))) {
                String line = reader.readLine(); //skip headers

                while ((line = reader.readLine()) != null) {

                    String[] columns = line.split(",");

                    try {
                        results.add(new Vehicle(
                                getId(columns),
                                getPlateNumber(columns),
                                getFirstRegistration(columns),
                                getPurchasePrice(columns),
                                getProducer(columns),
                                getMileage(columns),
                                getPreviousIndemnity(columns)));
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                        LOG.info(String.format("Not processed csv line: %s", line));
                    }
                }
            }
        } catch (IOException e) {
            return null;
        }

        return results;
    }

    private Long getId(String[] columns) {
        return Long.parseLong(columns[0]) + 1;
    }

    private String getPlateNumber(String[] columns) {
        return columns[1];
    }

    private short getFirstRegistration(String[] columns) {
        return Short.parseShort(columns[2]);
    }

    private double getPurchasePrice(String[] columns) {
        return Double.parseDouble(columns[3]);
    }

    private String getProducer(String[] columns) {
        return columns[4];
    }

    private double getMileage(String[] columns) {
        return Double.parseDouble(columns[5]);
    }

    private double getPreviousIndemnity(String[] columns) {
        return Double.parseDouble(columns[6]);
    }
}
