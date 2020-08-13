package com.example.insurance.repositories.jpa;

import com.example.insurance.model.InsuranceCalcResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceCalcResultRepository extends JpaRepository<InsuranceCalcResult, Long> {
}
