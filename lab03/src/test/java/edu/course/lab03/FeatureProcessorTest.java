package edu.course.lab03;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FeatureProcessorTest {
    @Test
    void canReplaceNormalizationStrategyWithoutChangingProcessor() {
        double[] values = {2.0, 4.0, 6.0};
        FeatureProcessor minMaxProcessor =
                new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor meanCenteringProcessor =
                new FeatureProcessor(new MeanCenteringNormalization());

        assertArrayEquals(new double[]{0.0, 0.5, 1.0},
                minMaxProcessor.process(values), 1.0e-12);
        assertArrayEquals(new double[]{-2.0, 0.0, 2.0},
                meanCenteringProcessor.process(values), 1.0e-12);
        assertArrayEquals(new double[]{2.0, 4.0, 6.0}, values);
    }

    @Test
    void canUseTheAdditionalZScoreStrategy() {
        FeatureProcessor processor = new FeatureProcessor(new ZScoreNormalization());

        assertArrayEquals(new double[]{-Math.sqrt(1.5), 0.0, Math.sqrt(1.5)},
                processor.process(new double[]{1.0, 2.0, 3.0}), 1.0e-12);
    }

    @Test
    void requiresAStrategyAndNonNullInput() {
        assertThrows(NullPointerException.class, () -> new FeatureProcessor(null));
        FeatureProcessor processor = new FeatureProcessor(new MinMaxNormalization());
        assertThrows(NullPointerException.class, () -> processor.process(null));
    }
}



