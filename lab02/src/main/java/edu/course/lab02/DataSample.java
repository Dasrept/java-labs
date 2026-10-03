package edu.course.lab02;

import java.util.Objects;

public final class DataSample {
    private final String id;
    private final String label;
    private SampleStatus status;
    private final double[] features;

    public DataSample(String id, String label, SampleStatus status, double[] features) {
        this.id = requireText(id, "id");
        this.label = requireText(label, "label");
        this.status = Objects.requireNonNull(status, "status");
        Objects.requireNonNull(features, "features");
        if (features.length == 0) {
            throw new IllegalArgumentException("Features cannot be empty");
        }
        this.features = features.clone();
        for (double feature : this.features) {
            if (!Double.isFinite(feature)) {
                throw new IllegalArgumentException("Features must be finite numbers");
            }
        }
    }

    public static DataSample withId(SampleId id, String label, SampleStatus status,
                                   double[] features) {
        Objects.requireNonNull(id, "id");
        return new DataSample(id.value(), label, status, features);
    }

    public String getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public SampleStatus getStatus() {
        return status;
    }

    public double[] getFeatures() {
        return features.clone();
    }

    public void changeStatus(SampleStatus newStatus) {
        status = Objects.requireNonNull(newStatus, "newStatus");
    }

    public boolean isReady() {
        return status == SampleStatus.READY;
    }

    public double averageFeature() {
        double average = 0.0;
        for (double feature : features) {
            average += feature / features.length;
        }
        return average;
    }

    public double[] normalizedFeatures() {
        double minimum = features[0];
        double maximum = features[0];
        double scale = Math.abs(features[0]);
        for (int i = 1; i < features.length; i++) {
            minimum = Math.min(minimum, features[i]);
            maximum = Math.max(maximum, features[i]);
            scale = Math.max(scale, Math.abs(features[i]));
        }

        double[] normalized = new double[features.length];
        if (scale == 0.0) {
            return normalized;
        }
        double scaledMinimum = minimum / scale;
        double scaledRange = maximum / scale - scaledMinimum;
        if (scaledRange == 0.0) {
            return normalized;
        }
        for (int i = 0; i < features.length; i++) {
            normalized[i] = (features[i] / scale - scaledMinimum) / scaledRange;
        }
        return normalized;
    }

    private static String requireText(String value, String name) {
        Objects.requireNonNull(value, name);
        if (value.isBlank()) {
            throw new IllegalArgumentException(name + " cannot be blank");
        }
        return value;
    }
}
