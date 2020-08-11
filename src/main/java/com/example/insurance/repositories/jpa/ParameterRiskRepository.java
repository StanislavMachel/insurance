package com.example.insurance.repositories.jpa;

import com.example.insurance.model.ParameterRisk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParameterRiskRepository extends JpaRepository<ParameterRisk, Long> {
    List<ParameterRisk> findAllByParameterNameIn(List<String> parameterNames);
}
