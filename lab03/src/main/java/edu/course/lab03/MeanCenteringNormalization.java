package edu.course.lab03;

import java.util.Objects;

public final class MeanCenteringNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        validate(values);

        double mean = 0.0;
        for (double value : values) {
            mean += value / values.length;
        }

        double[] normalized = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            normalized[i] = values[i] - mean;
        }
        return normalized;
    }

    private static void validate(double[] values) {
        Objects.requireNonNull(values, "values");
        if (values.length == 0) {
            throw new IllegalArgumentException("Values cannot be empty");
        }
        for (double value : values) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Values must be finite numbers");
            }
        }
    }
}

