package com.example.insurance.utils;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void calculateAnnualFee() {
        double result = Calculator.calculateAnnualFee(49687, 0.99, 1.1, 5);
        Assert.assertEquals(2705.45715, result, 0.0001);
    }

    @Test
    public void calculateMonthlyFee() {
        double result = Calculator.calculateMonthlyFee(49687, 0.99, 1.1, 5);
        Assert.assertEquals(225.4547625, result, 0.0001);
    }
}