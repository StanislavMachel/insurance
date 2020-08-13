package com.example.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParameterRisk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String parameterName;
    private double coefficient;

    protected ParameterRisk() {

    }

    public ParameterRisk(String parameterName, double coefficient) {
        this.parameterName = parameterName;
        this.coefficient = coefficient;
    }

    public String getParameterName() {
        return parameterName;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
