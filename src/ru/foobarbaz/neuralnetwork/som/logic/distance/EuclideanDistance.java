package ru.foobarbaz.neuralnetwork.som.logic.distance;

import ru.foobarbaz.neuralnetwork.common.ArraysHelper;

public class EuclideanDistance implements Distance {

    @Override
    public Double apply(double[] a, double[] b) {
        double squareEuclideanDistance = ArraysHelper.mergeByBiFunction(a, b, (x, y) -> (x - y) * (x - y)).sum();
        return Math.sqrt(squareEuclideanDistance);
    }

    @Override
    public String toString() {
        return "Euclidean Distance";
    }
}
