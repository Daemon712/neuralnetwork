package ru.foobarbaz.neuralnetwork.function;

import java.util.function.Function;

public class GaussianFunction implements Function<Double, Double> {
    private static final double SQRT_2PI = Math.sqrt(2*Math.PI);
    private double sigma, mu;

    public GaussianFunction(double sigma, double mu) {
        this.sigma = sigma;
        this.mu = mu;
    }

    public GaussianFunction() {
        this(1, 0);
    }

    @Override
    public Double apply(Double x) {
        return 1/(sigma * SQRT_2PI) * Math.exp(-(x - mu)*(x - mu)/(2 * sigma * sigma));
    }
}
