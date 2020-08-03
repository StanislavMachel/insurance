package com.example.insurance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Data {

    @JsonProperty("coefficients")
    private Map<String, Double> coefficients;

    @JsonProperty("make_coefficients")
    private Map<String, Double> makeCoefficients;

    @JsonProperty("avg_purchase_price")
    private Map<String, Double> avgPurchasePrice;

    public Map<String, Double> getCoefficients() {
        return coefficients;
    }

    public Map<String, Double> getMakeCoefficients() {
        return makeCoefficients;
    }

    public Map<String, Double> getAvgPurchasePrice() {
        return avgPurchasePrice;
    }
}