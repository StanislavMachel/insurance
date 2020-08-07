package com.example.insurance.migration;

import com.example.insurance.model.Vehicle;
import com.example.insurance.repositories.csv.VehicleCsvRepository;
import com.example.insurance.repositories.jpa.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbMigration {

    private static Logger LOG = LoggerFactory.getLogger(DbMigration.class);

    private final VehicleCsvRepository vehicleCsvRepository;
    private final VehicleRepository vehicleRepository;

    public DbMigration(VehicleCsvRepository vehicleCsvRepository,
                       VehicleRepository vehicleRepository) {
        this.vehicleCsvRepository = vehicleCsvRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public void run() {
        LOG.info("Start migration to memory database");

        List<Vehicle> vehicles = vehicleCsvRepository.findAll();

        vehicleRepository.saveAll(vehicles);

        LOG.info("Migrated {} items", vehicleRepository.count());
        LOG.info("Migration to memory database ends...");

    }
}
