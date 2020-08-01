package com.example.insurance.repositories;

import com.example.insurance.model.Vehicle;

import java.util.List;

public interface VehicleRepository {
  List<Vehicle> findAll();
}
