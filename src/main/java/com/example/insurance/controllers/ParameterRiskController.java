package com.example.insurance.controllers;

import com.example.insurance.model.ParameterRisk;
import com.example.insurance.repositories.jpa.ParameterRiskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(ParameterRiskController.URL)
@RestController
public class ParameterRiskController {
    public static final String URL = "/api/parameter-risk";
    private final ParameterRiskRepository parameterRiskRepository;

    public ParameterRiskController(ParameterRiskRepository parameterRiskRepository) {
        this.parameterRiskRepository = parameterRiskRepository;
    }

    @GetMapping
    public ResponseEntity<Page<ParameterRisk>> getAll(Pageable pageable) {
        return ResponseEntity.ok(parameterRiskRepository.findAll(pageable));
    }
}
