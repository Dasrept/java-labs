package edu.course.lab04;

import java.util.HashMap;
import java.util.Objects;

public final class SampleRegistry {
    private final HashMap<SampleId, DataSample> samples = new HashMap<>();

    public DataSample put(SampleId id, DataSample sample) {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(sample, "sample");
        return samples.put(id, sample);
    }

    public DataSample get(SampleId id) {
        Objects.requireNonNull(id, "id");
        return samples.get(id);
    }

    public DataSample remove(SampleId id) {
        Objects.requireNonNull(id, "id");
        return samples.remove(id);
    }

    public boolean containsKey(SampleId id) {
        Objects.requireNonNull(id, "id");
        return samples.containsKey(id);
    }

    public int size() {
        return samples.size();
    }
}

