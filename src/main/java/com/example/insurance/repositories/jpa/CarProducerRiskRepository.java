package com.example.insurance.repositories.jpa;

import com.example.insurance.model.CarProducerRisk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarProducerRiskRepository extends JpaRepository<CarProducerRisk, Long> {
    CarProducerRisk findByCarProducer(String carProducer);
}
