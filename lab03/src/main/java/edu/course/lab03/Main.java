package edu.course.lab03;

import java.util.Arrays;

public final class Main {
    public static void main(String[] args) {
        PriceCalculator regularPrice = new PriceCalculator(new NoDiscount());
        PriceCalculator discountedPrice = new PriceCalculator(new PercentDiscount(20.0));
        System.out.println("Price without discount: "
                + regularPrice.calculateFinalPrice(100.0));
        System.out.println("Price with 20% discount: "
                + discountedPrice.calculateFinalPrice(100.0));

        double[] features = {2.0, 4.0, 6.0};
        FeatureProcessor minMaxProcessor =
                new FeatureProcessor(new MinMaxNormalization());
        FeatureProcessor meanCenteringProcessor =
                new FeatureProcessor(new MeanCenteringNormalization());
        FeatureProcessor zScoreProcessor =
                new FeatureProcessor(new ZScoreNormalization());

        System.out.println("Min-max: "
                + Arrays.toString(minMaxProcessor.process(features)));
        System.out.println("Mean-centering: "
                + Arrays.toString(meanCenteringProcessor.process(features)));
        System.out.println("Z-score: "
                + Arrays.toString(zScoreProcessor.process(features)));
        System.out.println("Original features: " + Arrays.toString(features));
    }
}
