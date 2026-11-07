package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void shouldCalculatePriceWithoutDiscount() {
        PriceCalculator calculator =
                new PriceCalculator(new NoDiscount());

        assertEquals(100, calculator.calculate(100));
    }

    @Test
    void shouldReplaceDiscountPolicy() {
        PriceCalculator noDiscount =
                new PriceCalculator(new NoDiscount());

        PriceCalculator percentDiscount =
                new PriceCalculator(new PercentDiscount(20));

        assertEquals(100, noDiscount.calculate(100));
        assertEquals(80, percentDiscount.calculate(100), 1e-9);
    }
}

