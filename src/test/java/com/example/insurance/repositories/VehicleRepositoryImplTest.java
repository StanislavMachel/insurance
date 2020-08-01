package com.example.insurance.repositories;

import com.example.insurance.model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VehicleRepositoryImplTest {

  private VehicleRepository vehicleRepository;

  @Before
  public void setUp() {
    vehicleRepository = new VehicleRepositoryImpl();
  }

  @Test
  public void testFindAll() {
    List<Vehicle> vehicles = vehicleRepository.findAll();

    Assert.assertNotNull(vehicles);
    Assert.assertFalse(vehicles.isEmpty());
    Assert.assertEquals(3, vehicles.size());


    Vehicle firstVehicle = vehicles.get(0);
    Assert.assertNotNull(firstVehicle);
    Assert.assertEquals("258XCC", firstVehicle.getPlateNumber());
    Assert.assertEquals(2013, firstVehicle.getFirstRegistration());
    Assert.assertEquals("FORD", firstVehicle.getProducer());
    Assert.assertEquals(217126, firstVehicle.getMileage(), 0.001);
    Assert.assertEquals(10482.93, firstVehicle.getPreviousIndemnity(), 0.001);
  }

}