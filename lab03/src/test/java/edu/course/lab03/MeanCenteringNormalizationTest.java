package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeanCenteringNormalizationTest {
    private final MeanCenteringNormalization normalization =
            new MeanCenteringNormalization();

    @Test
    void subtractsMeanFromEveryValue() {
        double[] values = {1.0, 2.0, 3.0};

        assertArrayEquals(new double[]{-1.0, 0.0, 1.0},
                normalization.normalize(values), 1.0e-12);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, values);
    }

    @Test
    void returnsZerosForConstantValuesAndHandlesLargeValues() {
        assertArrayEquals(new double[]{0.0, 0.0},
                normalization.normalize(new double[]{5.0, 5.0}));
        assertArrayEquals(new double[]{-Double.MAX_VALUE, Double.MAX_VALUE},
                normalization.normalize(new double[]{-Double.MAX_VALUE, Double.MAX_VALUE}));
    }

    @Test
    void rejectsNullEmptyAndNonFiniteInput() {
        assertThrows(NullPointerException.class, () -> normalization.normalize(null));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{}));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{Double.POSITIVE_INFINITY}));
    }
}

