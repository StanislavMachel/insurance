package com.example.insurance.controllers;

import com.example.insurance.model.InsuranceCalcResult;
import com.example.insurance.repositories.jpa.InsuranceCalcResultRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(InsuranceCalcResultController.URL)
@RestController
public class InsuranceCalcResultController {
    public static final String URL = "/api/insurance-calc-result";
    private final InsuranceCalcResultRepository insuranceCalcResultRepository;

    public InsuranceCalcResultController(InsuranceCalcResultRepository insuranceCalcResultRepository) {
        this.insuranceCalcResultRepository = insuranceCalcResultRepository;
    }

    @GetMapping
    public ResponseEntity<Page<InsuranceCalcResult>> getAll(@RequestParam(required = false) List<String> includeParameterNames, Pageable pageable) {
        Page<InsuranceCalcResult> page;
        if (includeParameterNames != null) {
            page = insuranceCalcResultRepository.findAllByIncludeParameterNamesListWithPagination(includeParameterNames, (long) includeParameterNames.size(), pageable);
        } else {
            page = insuranceCalcResultRepository.findAll(pageable);
        }

        return ResponseEntity.ok(page);
    }
}
