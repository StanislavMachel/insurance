package com.example.insurance.repositories.jpa;

import com.example.insurance.model.AvgPurchasePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvgPurchasePriceRepository extends JpaRepository<AvgPurchasePrice, Long> {
    AvgPurchasePrice findByCarProducer(String carProducer);
}
