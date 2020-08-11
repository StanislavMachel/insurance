package com.example.insurance.repositories.jpa;

import com.example.insurance.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleJpaRepository extends JpaRepository<Vehicle, Long> {
}
