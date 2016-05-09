package ru.foobarbaz.neuralnetwork.som.logic.distance;

import ru.foobarbaz.neuralnetwork.common.ArraysHelper;

public class MinkowskiDistance implements Distance {
    private double p;

    /**
     * @param p parameter in Minkowski's distance function
     *          p < 1 - bad metrics
     *          p = 1 - Manhattan distance
     *          p = 2 - Euclidean distance
     *          p -> positive infinity - Chebyshev distance
     */
    public MinkowskiDistance(double p) {
        this.p = p;
    }

    @Override
    public Double apply(double[] a, double[] b) {
        double sum = ArraysHelper.mergeByBiFunction(a, b, (x, y) -> Math.pow(Math.abs(x - y), p)).sum();
        return Math.pow(sum, 1/p);
    }

    @Override
    public String toString() {
        return "Minkowski Distance (p = " + p + ")";
    }
}
