package ru.foobarbaz.neuralnetwork.function;

import java.util.function.Function;

public class SigmoidFunction implements Function<Double, Double> {
    /**
     * @param x value between -infinity and +infinity
     * @return value between 0 and 1
     */
    @Override
    public Double apply(Double x) {
        return 1/(1+Math.exp(-x));
    }
}
