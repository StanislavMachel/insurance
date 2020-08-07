package com.example.insurance.repositories.csv;

import com.example.insurance.model.Vehicle;

import java.util.List;

public interface VehicleCsvRepository {
    List<Vehicle> findAll();
}
