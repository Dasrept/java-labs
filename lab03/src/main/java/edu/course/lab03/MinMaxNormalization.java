package edu.course.lab03;

import java.util.Objects;

public final class MinMaxNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        validate(values);

        double minimum = values[0];
        double maximum = values[0];
        double scale = Math.abs(values[0]);
        for (int i = 1; i < values.length; i++) {
            minimum = Math.min(minimum, values[i]);
            maximum = Math.max(maximum, values[i]);
            scale = Math.max(scale, Math.abs(values[i]));
        }

        double[] normalized = new double[values.length];
        if (scale == 0.0) {
            return normalized;
        }

        double scaledMinimum = minimum / scale;
        double scaledRange = maximum / scale - scaledMinimum;
        if (scaledRange == 0.0) {
            return normalized;
        }

        for (int i = 0; i < values.length; i++) {
            normalized[i] = (values[i] / scale - scaledMinimum) / scaledRange;
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

