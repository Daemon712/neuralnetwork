package ru.foobarbaz.neuralnetwork.distance;

import ru.foobarbaz.neuralnetwork.utils.ArraysHelper;

import java.util.OptionalDouble;
import java.util.function.BiFunction;

public class ChebyshevDistance implements BiFunction<double[], double[], Double> {
    @Override
    public Double apply(double[] a, double[] b) {
        OptionalDouble max = ArraysHelper.mergeByBiFunction(a, b, (x, y) -> Math.abs(x - y)).max();
        return max.isPresent() ? max.getAsDouble() : 0;
    }

    @Override
    public String toString() {
        return "Chebyshev Distance";
    }
}