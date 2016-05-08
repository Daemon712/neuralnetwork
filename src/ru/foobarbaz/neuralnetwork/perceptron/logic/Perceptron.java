package ru.foobarbaz.neuralnetwork.perceptron.logic;

public interface Perceptron {
    double[] process(double[] input);
    void study(double[] input, double[] expectedOutput);
}
