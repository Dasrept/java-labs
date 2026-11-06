package edu.course.lab04;

import java.util.Objects;

public final class SampleId {
    private final String datasetName;
    private final int observationNumber;

    public SampleId(String datasetName, int observationNumber) {
        Objects.requireNonNull(datasetName, "datasetName");
        if (datasetName.isBlank()) {
            throw new IllegalArgumentException("Dataset name cannot be blank");
        }
        if (observationNumber <= 0) {
            throw new IllegalArgumentException("Observation number must be positive");
        }
        this.datasetName = datasetName;
        this.observationNumber = observationNumber;
    }

    public String getDatasetName() {
        return datasetName;
    }

    public int getObservationNumber() {
        return observationNumber;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        SampleId other = (SampleId) object;
        return observationNumber == other.observationNumber
                && datasetName.equals(other.datasetName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(datasetName, observationNumber);
    }
}

