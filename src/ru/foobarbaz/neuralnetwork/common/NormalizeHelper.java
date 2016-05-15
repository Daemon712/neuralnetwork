package ru.foobarbaz.neuralnetwork.common;

public abstract class NormalizeHelper {
    public static double normalize(double min, double value, double max){
        return 2 * (value - min) / (max - min) - 1;
    }

    public static void assertNormal(double min, double value, double max){
        if (value < min || value > max) {
            throw new IllegalArgumentException(
                    String.format("Value=%f isn't normal. It must be between %f and %f", value, min, max)
            );
        }
    }
}
