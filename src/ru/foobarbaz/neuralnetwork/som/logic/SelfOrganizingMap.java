package ru.foobarbaz.neuralnetwork.som.logic;

public interface SelfOrganizingMap {
    int process(double[] input);
    void study(double[] input);
    void study(double[][] inputs);
    void study(double[][] inputs, int eras);
    void nextStudyingEra();
    double[][] getWeights();
    int getClusters();
}
