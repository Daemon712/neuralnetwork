package ru.foobarbaz.neuralnetwork.function;

import java.util.function.Function;

public class HyperbolicFunction implements Function<Double, Double>{
    private double a, b;

    public HyperbolicFunction(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public HyperbolicFunction() {
        this(1, 0);
    }

    @Override
    public Double apply(Double x) {
        return a / (x + b);
    }
}
