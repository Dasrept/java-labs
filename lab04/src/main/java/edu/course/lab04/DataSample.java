package edu.course.lab04;

import java.util.Objects;

public final class DataSample {
    private final String label;
    private final double[] features;

    public DataSample(String label, double[] features) {
        Objects.requireNonNull(label, "label");
        if (label.isBlank()) {
            throw new IllegalArgumentException("Label cannot be blank");
        }
        Objects.requireNonNull(features, "features");
        if (features.length == 0) {
            throw new IllegalArgumentException("Features cannot be empty");
        }
        this.label = label;
        this.features = features.clone();
        for (double feature : this.features) {
            if (!Double.isFinite(feature)) {
                throw new IllegalArgumentException("Features must be finite numbers");
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public double[] getFeatures() {
        return features.clone();
    }
}

