package ru.foobarbaz.neuralnetwork.teacher;

import ru.foobarbaz.neuralnetwork.network.StudyingWithTeacherNetwork;
import ru.foobarbaz.neuralnetwork.network.Perceptron;

public class DigitTeacher {
    private double[][] inputSet;
    private double[][] exceptedSet;
    private StudyingWithTeacherNetwork neuralNetwork;

    public DigitTeacher() {
        neuralNetwork = new Perceptron(new int[]{15, 12, 10});
        initInputSet();
        initExceptedSet();
    }

    public void study(int cycles) {
        for (int i = 0; i < cycles; i++) {
            for (int j = 0; j < inputSet.length; j++) {
                neuralNetwork.study(inputSet[j], exceptedSet[j]);
            }
        }
    }

    private void initInputSet() {
        inputSet = new double[10][];
        inputSet[1] = new double[]{
                0, 0, 1,
                0, 1, 1,
                0, 0, 1,
                0, 0, 1,
                0, 0, 1};
        inputSet[2] = new double[]{
                1, 1, 1,
                0, 0, 1,
                1, 1, 1,
                1, 0, 0,
                1, 1, 1};
        inputSet[3] = new double[]{
                1, 1, 1,
                0, 0, 1,
                1, 1, 1,
                0, 0, 1,
                1, 1, 1};
        inputSet[4] = new double[]{
                1, 0, 1,
                1, 0, 1,
                1, 1, 1,
                0, 0, 1,
                0, 0, 1};
        inputSet[5] = new double[]{
                1, 1, 1,
                1, 0, 0,
                1, 1, 1,
                0, 0, 1,
                1, 1, 1};
        inputSet[6] = new double[]{
                1, 1, 1,
                1, 0, 0,
                1, 1, 1,
                1, 0, 1,
                1, 1, 1};
        inputSet[7] = new double[]{
                1, 1, 1,
                0, 0, 1,
                0, 0, 1,
                0, 1, 0,
                0, 1, 0};
        inputSet[8] = new double[]{
                1, 1, 1,
                1, 0, 1,
                1, 1, 1,
                1, 0, 1,
                1, 1, 1};
        inputSet[9] = new double[]{
                1, 1, 1,
                1, 0, 1,
                1, 1, 1,
                0, 0, 1,
                1, 1, 1};
        inputSet[0] = new double[]{
                1, 1, 1,
                1, 0, 1,
                1, 0, 1,
                1, 0, 1,
                1, 1, 1};
    }

    private void initExceptedSet() {
        exceptedSet = new double[10][];
        exceptedSet[0] = new double[]{1, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        exceptedSet[1] = new double[]{0, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        exceptedSet[2] = new double[]{0, 0, 1, 0, 0, 0, 0, 0, 0, 0};
        exceptedSet[3] = new double[]{0, 0, 0, 1, 0, 0, 0, 0, 0, 0};
        exceptedSet[4] = new double[]{0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
        exceptedSet[5] = new double[]{0, 0, 0, 0, 0, 1, 0, 0, 0, 0};
        exceptedSet[6] = new double[]{0, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        exceptedSet[7] = new double[]{0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        exceptedSet[8] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 0};
        exceptedSet[9] = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 1};
    }

    public StudyingWithTeacherNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

}
