package com.example.insurance.controllers;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.repositories.jpa.InsuranceCalcResultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(InsuranceCalcResultController.URL)
@RestController
public class InsuranceCalcResultController {
    public static final String URL = "/api/insurance-calc-result";
    private final InsuranceCalcResultRepository insuranceCalcResultRepository;

    public InsuranceCalcResultController(InsuranceCalcResultRepository insuranceCalcResultRepository) {
        this.insuranceCalcResultRepository = insuranceCalcResultRepository;
    }

    @GetMapping
    public ResponseEntity<Page<InsuranceCalcResult>> getAll(Pageable pageable) {
        return ResponseEntity.ok(insuranceCalcResultRepository.findAll(pageable));
    }
}
