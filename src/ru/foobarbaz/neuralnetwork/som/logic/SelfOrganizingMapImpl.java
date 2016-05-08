package ru.foobarbaz.neuralnetwork.som.logic;

public class SelfOrganizingMapImpl implements SelfOrganizingMap {
    private double[][] weights;

    public SelfOrganizingMapImpl(int inputNeurons, int outputNeurons) {
        initWeights(inputNeurons, outputNeurons);
    }

    @Override
    public void study(double[] input) {

    }

    @Override
    public int process(double[] input) {
        return 0;
    }

    private void initWeights(int inputNeurons, int outputNeurons){
        weights = new double[outputNeurons][inputNeurons];
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                weights[i][j] = Math.random() - 0.5;
            }
        }
    }
}
