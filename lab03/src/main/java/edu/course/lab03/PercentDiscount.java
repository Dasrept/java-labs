package edu.course.lab03;

public final class PercentDiscount implements DiscountPolicy {
    private final double percent;

    public PercentDiscount(double percent) {
        if (!Double.isFinite(percent) || percent < 0.0 || percent > 100.0) {
            throw new IllegalArgumentException("Percent must be between 0 and 100");
        }
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        if (price < 0.0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return price * (1.0 - percent / 100.0);
    }
}

