package com.example.insurance.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CarProducerRisk {
    @Id
    @GeneratedValue
    private Long id;
    private String carProducer;
    private double coefficient;

    protected CarProducerRisk() {

    }

    public CarProducerRisk(String carProducer, double coefficient) {
        this.carProducer = carProducer;
        this.coefficient = coefficient;
    }

    public String getCarProducer() {
        return carProducer;
    }

    public double getCoefficient() {
        return coefficient;
    }
}
