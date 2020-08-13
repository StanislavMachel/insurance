package com.example.insurance.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void calculateAnnualFee3Coeff() {
        double result = Calculator.calculateAnnualFee(0.99,
                new ParamValueCoefficient(49687, 0.08),
                new ParamValueCoefficient(5, 1.1));
        //0.99 * (49687 * 0.08 + 1.1 * 5) = 0.99 * (3974.96 + 5.5) = 3940.6554
        assertEquals(3940.6554, result, 0.01);
    }

    @Test
    public void calculateAnnualFee4Coeff() {

        double result = Calculator.calculateAnnualFee(0.99,
                new ParamValueCoefficient(49687, 0.08),
                new ParamValueCoefficient(5, 1.1),
                new ParamValueCoefficient(10482.93, 0.05));

        //0.99 * (49687 * 0.08 + 1.1 * 5 + 10482.93 * 0.05) = 0.99 * (3974.96 + 5.5 + 524.1465) = 4459.560435
        assertEquals(4459.560435, result, 0.01);
    }
}