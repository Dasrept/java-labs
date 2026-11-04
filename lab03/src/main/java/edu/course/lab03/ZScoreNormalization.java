package edu.course.lab03;

import java.util.Objects;

public final class ZScoreNormalization implements NormalizationStrategy {
    @Override
    public double[] normalize(double[] values) {
        validate(values);

        double scale = 0.0;
        for (double value : values) {
            scale = Math.max(scale, Math.abs(value));
        }

        double[] normalized = new double[values.length];
        if (scale == 0.0) {
            return normalized;
        }

        double mean = 0.0;
        for (double value : values) {
            mean += (value / scale) / values.length;
        }

        double variance = 0.0;
        for (double value : values) {
            double difference = value / scale - mean;
            variance += difference * difference / values.length;
        }

        double standardDeviation = Math.sqrt(variance);
        if (standardDeviation == 0.0) {
            return normalized;
        }

        for (int i = 0; i < values.length; i++) {
            normalized[i] = (values[i] / scale - mean) / standardDeviation;
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

