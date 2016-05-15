package ru.foobarbaz.neuralnetwork.function.distance;

import ru.foobarbaz.neuralnetwork.common.ArraysHelper;

public class MinkowskiDistance implements DistanceFunction {
    private double parameter;

    /**
     * @param parameter parameter in Minkowski's distance function
     *          parameter < 1 - bad metrics
     *          parameter = 1 - Manhattan distance
     *          parameter = 2 - Euclidean distance
     *          parameter -> positive infinity - Chebyshev distance
     */
    public MinkowskiDistance(double parameter) {
        this.parameter = parameter;
    }

    public MinkowskiDistance() {
        this(2);
    }

    @Override
    public Double apply(double[] a, double[] b) {
        double sum = ArraysHelper.mergeByBiFunction(a, b, (x, y) -> Math.pow(Math.abs(x - y), parameter)).sum();
        return Math.pow(sum, 1/ parameter);
    }

    public void setParameter(double p) {
        this.parameter = p;
    }

    public double getParameter() {
        return parameter;
    }

    @Override
    public String toString() {
        return "Minkowski Distance (p = " + parameter + ")";
    }
}
