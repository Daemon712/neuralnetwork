package ru.foobarbaz.neuralnetwork.common;

import java.util.function.BiFunction;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public abstract class ArraysHelper {
    public static  <In1, In2, Out> Stream<Out> mergeByBiFunction(In1[] array1, In2[] array2, BiFunction<In1, In2, Out> function){
        int length = Math.min(array1.length, array2.length);
        Stream.Builder<Out> streamBuilder = Stream.builder();
        for (int i = 0; i < length; i++) {
            streamBuilder.accept(function.apply(array1[i], array2[i]));
        }
        return streamBuilder.build();
    }

    public static DoubleStream mergeByBiFunction(double[] array1, double[] array2, BiFunction<Double, Double, Double> function){
        int length = Math.min(array1.length, array2.length);
        DoubleStream.Builder streamBuilder = DoubleStream.builder();
        for (int i = 0; i < length; i++) {
            streamBuilder.accept(function.apply(array1[i], array2[i]));
        }
        return streamBuilder.build();
    }
}
