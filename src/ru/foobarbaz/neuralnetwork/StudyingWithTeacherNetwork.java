package ru.foobarbaz.neuralnetwork;

public interface StudyingWithTeacherNetwork extends NeuralNetwork {
    void study(double[] input, double[] expectedOutput);
}
