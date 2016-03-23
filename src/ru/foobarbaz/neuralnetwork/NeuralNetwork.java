package ru.foobarbaz.neuralnetwork;

public interface NeuralNetwork {
    double[] process(double[] input);
    void study(double[] input, double[] expectedOutput);
}
