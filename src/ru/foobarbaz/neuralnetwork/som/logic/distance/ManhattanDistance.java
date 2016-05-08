package ru.foobarbaz.neuralnetwork.som.logic.distance;

import ru.foobarbaz.neuralnetwork.common.ArraysHelper;

import java.util.function.BiFunction;

public class ManhattanDistance implements BiFunction<double[], double[], Double> {
    @Override
    public Double apply(double[] a, double[] b) {
        return ArraysHelper.mergeByBiFunction(a, b, (x, y) -> Math.abs(x - y)).sum();
    }

    @Override
    public String toString() {
        return "Manhattan Distance";
    }
}
