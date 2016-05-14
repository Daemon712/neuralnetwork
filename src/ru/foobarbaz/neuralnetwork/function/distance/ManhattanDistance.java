package ru.foobarbaz.neuralnetwork.function.distance;

import ru.foobarbaz.neuralnetwork.common.ArraysHelper;

public class ManhattanDistance implements Distance {
    @Override
    public Double apply(double[] a, double[] b) {
        return ArraysHelper.mergeByBiFunction(a, b, (x, y) -> Math.abs(x - y)).sum();
    }

    @Override
    public String toString() {
        return "Manhattan Distance";
    }
}
