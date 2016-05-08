package ru.foobarbaz.neuralnetwork.network;

public interface StudyingWithTeacherNetwork extends NeuralNetwork {
    void study(double[] input, double[] expectedOutput);
}
