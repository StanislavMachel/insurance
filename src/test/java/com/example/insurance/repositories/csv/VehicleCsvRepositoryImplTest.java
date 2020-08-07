package com.example.insurance.repositories.csv;

import com.example.insurance.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleCsvRepositoryImplTest {

    private VehicleCsvRepository vehicleCsvRepository;

    @BeforeEach
    public void setUp() {
        vehicleCsvRepository = new VehicleCsvRepositoryImpl();
    }

    @Test
    public void testFindAll() {
        List<Vehicle> vehicles = vehicleCsvRepository.findAll();

        assertNotNull(vehicles);
        assertFalse(vehicles.isEmpty());
        assertEquals(3, vehicles.size());


        Vehicle firstVehicle = vehicles.get(0);
        assertNotNull(firstVehicle);
        assertEquals(1, firstVehicle.getId());
        assertEquals("258XCC", firstVehicle.getPlateNumber());
        assertEquals(2013, firstVehicle.getFirstRegistration());
        assertEquals("FORD", firstVehicle.getProducer());
        assertEquals(217126, firstVehicle.getMileage(), 0.001);
        assertEquals(10482.93, firstVehicle.getPreviousIndemnity(), 0.001);
    }

}