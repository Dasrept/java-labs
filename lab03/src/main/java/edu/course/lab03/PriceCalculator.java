package edu.course.lab03;

import java.util.Objects;

public final class PriceCalculator {
    private final DiscountPolicy discountPolicy;

    public PriceCalculator(DiscountPolicy discountPolicy) {
        this.discountPolicy = Objects.requireNonNull(discountPolicy, "discountPolicy");
    }

    public double calculateFinalPrice(double price) {
        return discountPolicy.apply(price);
    }
}

