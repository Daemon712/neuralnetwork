package ru.foobarbaz.neuralnetwork.distance;

import java.util.function.BiFunction;

public class EuclideanDistance implements BiFunction<double[], double[], Double> {
    private SquareEuclideanDistance square = new SquareEuclideanDistance();

    @Override
    public Double apply(double[] a, double[] b) {
        return Math.sqrt(square.apply(a,b));
    }
}
