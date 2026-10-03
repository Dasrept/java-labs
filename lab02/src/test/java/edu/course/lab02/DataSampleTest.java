package edu.course.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataSampleTest {
    @Test
    void createsChangesStatusAndCalculatesAverage() {
        DataSample sample = new DataSample("S-1", "cat", SampleStatus.NEW,
                new double[]{2.0, 4.0, 6.0});

        assertEquals("S-1", sample.getId());
        assertEquals("cat", sample.getLabel());
        assertFalse(sample.isReady());
        assertEquals(4.0, sample.averageFeature());

        sample.changeStatus(SampleStatus.READY);

        assertTrue(sample.isReady());
    }

    @Test
    void rejectsNullOrBlankId() {
        assertThrows(NullPointerException.class,
                () -> new DataSample(null, "label", SampleStatus.NEW, new double[]{1}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample(" ", "label", SampleStatus.NEW, new double[]{1}));
    }

    @Test
    void rejectsNullOrBlankLabel() {
        assertThrows(NullPointerException.class,
                () -> new DataSample("id", null, SampleStatus.NEW, new double[]{1}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("id", " ", SampleStatus.NEW, new double[]{1}));
    }

    @Test
    void rejectsNullStatusAndInvalidFeatures() {
        assertThrows(NullPointerException.class,
                () -> new DataSample("id", "label", null, new double[]{1}));
        assertThrows(NullPointerException.class,
                () -> new DataSample("id", "label", SampleStatus.NEW, null));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("id", "label", SampleStatus.NEW, new double[]{}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("id", "label", SampleStatus.NEW,
                        new double[]{Double.NaN}));
        assertThrows(IllegalArgumentException.class,
                () -> new DataSample("id", "label", SampleStatus.NEW,
                        new double[]{Double.POSITIVE_INFINITY}));
    }

    @Test
    void copiesFeaturesOnConstructionAndOnAccess() {
        double[] original = {1.0, 3.0};
        DataSample sample = new DataSample("id", "label", SampleStatus.NEW, original);
        original[0] = 100.0;

        double[] returned = sample.getFeatures();
        returned[1] = 200.0;

        assertArrayEquals(new double[]{1.0, 3.0}, sample.getFeatures());
        assertEquals(2.0, sample.averageFeature());
    }

    @Test
    void calculatesAverageWithoutOverflowingIntermediateSum() {
        DataSample sample = new DataSample("id", "label", SampleStatus.NEW,
                new double[]{Double.MAX_VALUE, Double.MAX_VALUE});

        assertEquals(Double.MAX_VALUE, sample.averageFeature());
    }

    @Test
    void normalizesFeaturesWithoutChangingTheSample() {
        DataSample sample = new DataSample("id", "label", SampleStatus.NEW,
                new double[]{2.0, 4.0, 6.0});

        assertArrayEquals(new double[]{0.0, 0.5, 1.0}, sample.normalizedFeatures(), 1.0e-12);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, sample.getFeatures());
    }

    @Test
    void normalizesConstantAndExtremeFeatures() {
        DataSample constant = new DataSample("constant", "label", SampleStatus.NEW,
                new double[]{5.0, 5.0});
        DataSample extreme = new DataSample("extreme", "label", SampleStatus.NEW,
                new double[]{-Double.MAX_VALUE, Double.MAX_VALUE});

        assertArrayEquals(new double[]{0.0, 0.0}, constant.normalizedFeatures());
        assertArrayEquals(new double[]{0.0, 1.0}, extreme.normalizedFeatures());
    }

    @Test
    void sampleIdIsAnImmutableValue() {
        SampleId first = new SampleId("S-1");
        SampleId second = new SampleId("S-1");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
        assertEquals("S-1", DataSample.withId(first, "label", SampleStatus.NEW,
                new double[]{1.0}).getId());
        assertThrows(IllegalArgumentException.class, () -> new SampleId(" "));
    }

    @Test
    void rejectsNullStatusChange() {
        DataSample sample = new DataSample("id", "label", SampleStatus.NEW, new double[]{1});

        assertThrows(NullPointerException.class, () -> sample.changeStatus(null));

        assertEquals(SampleStatus.NEW, sample.getStatus());
    }
}
