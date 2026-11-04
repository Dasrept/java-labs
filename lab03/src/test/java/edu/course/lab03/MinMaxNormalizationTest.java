package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MinMaxNormalizationTest {
    private final MinMaxNormalization normalization = new MinMaxNormalization();

    @Test
    void mapsMinimumAndMaximumToZeroAndOne() {
        double[] values = {2.0, 4.0, 6.0};

        assertArrayEquals(new double[]{0.0, 0.5, 1.0},
                normalization.normalize(values), 1.0e-12);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, values);
    }

    @Test
    void returnsZerosForConstantValuesAndHandlesExtremeRange() {
        assertArrayEquals(new double[]{0.0, 0.0},
                normalization.normalize(new double[]{5.0, 5.0}));
        assertArrayEquals(new double[]{0.0, 1.0},
                normalization.normalize(new double[]{-Double.MAX_VALUE, Double.MAX_VALUE}));
    }

    @Test
    void rejectsNullEmptyAndNonFiniteInput() {
        assertThrows(NullPointerException.class, () -> normalization.normalize(null));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{}));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{Double.NaN}));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{Double.NEGATIVE_INFINITY}));
    }
}

