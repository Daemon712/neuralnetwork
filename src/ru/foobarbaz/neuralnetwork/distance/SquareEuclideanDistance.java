package ru.foobarbaz.neuralnetwork.distance;

import ru.foobarbaz.neuralnetwork.utils.ArraysHelper;

import java.util.function.BiFunction;

public class SquareEuclideanDistance implements BiFunction<double[], double[], Double> {
    @Override
    public Double apply(double[] a, double[] b) {
        return ArraysHelper.mergeByBiFunction(a, b, (x, y) -> (x - y) * (x - y)).sum();
    }
}