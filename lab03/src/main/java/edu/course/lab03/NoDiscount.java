package edu.course.lab03;

public final class NoDiscount implements DiscountPolicy {
    @Override
    public double apply(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return price;
    }
}

