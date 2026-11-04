package edu.course.lab03;

import java.util.Objects;

public final class FeatureProcessor {
    private final NormalizationStrategy normalizationStrategy;

    public FeatureProcessor(NormalizationStrategy normalizationStrategy) {
        this.normalizationStrategy =
                Objects.requireNonNull(normalizationStrategy, "normalizationStrategy");
    }

    public double[] process(double[] values) {
        Objects.requireNonNull(values, "values");
        return normalizationStrategy.normalize(values.clone());
    }
}

