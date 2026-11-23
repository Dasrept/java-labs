package edu.course.lab02;

import java.util.Objects;

public record SampleId(String value) {
    public SampleId {
        Objects.requireNonNull(value, "value");
        if (value.isBlank()) {
            throw new IllegalArgumentException("Sample id cannot be blank");
        }
    }
}
