package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DiscountPolicyTest {
    @Test
    void noDiscountReturnsOriginalPrice() {
        assertEquals(100.0, new NoDiscount().apply(100.0));
    }

    @Test
    void percentDiscountReducesPrice() {
        assertEquals(80.0, new PercentDiscount(20.0).apply(100.0));
    }

    @Test
    void percentDiscountAcceptsBoundaryPercentages() {
        assertEquals(100.0, new PercentDiscount(0.0).apply(100.0));
        assertEquals(0.0, new PercentDiscount(100.0).apply(100.0));
    }

    @Test
    void rejectsInvalidPercentages() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(-1.0));
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(100.1));
        assertThrows(IllegalArgumentException.class,
                () -> new PercentDiscount(Double.NaN));
    }

    @Test
    void bothPoliciesRejectNegativePrices() {
        assertThrows(IllegalArgumentException.class, () -> new NoDiscount().apply(-1.0));
        assertThrows(IllegalArgumentException.class,
                () -> new PercentDiscount(20.0).apply(-1.0));
    }

    @Test
    void calculatorCanReplaceItsDiscountPolicy() {
        PriceCalculator noDiscountCalculator = new PriceCalculator(new NoDiscount());
        PriceCalculator discountedCalculator =
                new PriceCalculator(new PercentDiscount(20.0));

        assertEquals(100.0, noDiscountCalculator.calculateFinalPrice(100.0));
        assertEquals(80.0, discountedCalculator.calculateFinalPrice(100.0));
    }

    @Test
    void calculatorRequiresAPolicy() {
        assertThrows(NullPointerException.class, () -> new PriceCalculator(null));
    }
}

