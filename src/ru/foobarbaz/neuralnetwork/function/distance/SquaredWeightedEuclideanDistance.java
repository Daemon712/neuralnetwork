package ru.foobarbaz.neuralnetwork.function.distance;

import ru.foobarbaz.neuralnetwork.common.ArraysHelper;

public class SquaredWeightedEuclideanDistance implements Distance {
    private double weight;

    public SquaredWeightedEuclideanDistance() {
        this(1);
    }

    public SquaredWeightedEuclideanDistance(double weight) {
        this.weight = weight;
    }

    @Override
    public Double apply(double[] a, double[] b) {
        return weight * ArraysHelper.mergeByBiFunction(a, b, (x, y) -> (x - y) * (x - y)).sum();
    }

    @Override
    public String toString() {
        return "Squared Weighted Euclidean Distance (w = " + weight + ")";
    }
}
