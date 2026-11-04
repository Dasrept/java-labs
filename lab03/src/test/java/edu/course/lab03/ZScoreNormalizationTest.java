package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ZScoreNormalizationTest {
    private final ZScoreNormalization normalization = new ZScoreNormalization();

    @Test
    void producesZeroMeanAndUnitPopulationDeviation() {
        double[] values = {1.0, 2.0, 3.0};
        double rootOneAndAHalf = Math.sqrt(1.5);

        assertArrayEquals(new double[]{-rootOneAndAHalf, 0.0, rootOneAndAHalf},
                normalization.normalize(values), 1.0e-12);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, values);
    }

    @Test
    void returnsZerosForConstantValuesAndHandlesExtremeValues() {
        assertArrayEquals(new double[]{0.0, 0.0},
                normalization.normalize(new double[]{4.0, 4.0}));
        assertArrayEquals(new double[]{-1.0, 1.0},
                normalization.normalize(new double[]{-Double.MAX_VALUE, Double.MAX_VALUE}),
                1.0e-12);
    }

    @Test
    void rejectsNullEmptyAndNonFiniteInput() {
        assertThrows(NullPointerException.class, () -> normalization.normalize(null));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{}));
        assertThrows(IllegalArgumentException.class,
                () -> normalization.normalize(new double[]{Double.NaN}));
    }
}

