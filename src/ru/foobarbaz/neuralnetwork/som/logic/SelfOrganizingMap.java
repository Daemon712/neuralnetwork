package ru.foobarbaz.neuralnetwork.som.logic;

public interface SelfOrganizingMap {
    int process(double[] input);
    void study(double[] input);
}
