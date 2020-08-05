package com.example.insurance.utils;

import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {

    @Test
    public void calculateAnnualFee() {
        double result = Calculator.calculateAnnualFee(0.99, 49687, 0.08, 5, 1.1);
        //0.99 * (49687 * 0.08 + 1.1 * 5) = 0.99 * (3974.96 + 5.5) = 3940.6554
        Assert.assertEquals(3940.6554, result, 0.0001);
    }

    @Test
    public void calculateMonthlyFee() {
        double result = Calculator.calculateMonthlyFee(0.99, 49687, 0.08, 5, 1.1);
        Assert.assertEquals(328.38795, result, 0.0001);
    }
}