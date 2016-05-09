package ru.foobarbaz.neuralnetwork.som.logic;

import ru.foobarbaz.neuralnetwork.som.logic.distance.Distance;
import ru.foobarbaz.neuralnetwork.som.logic.distance.EuclideanDistance;

public class SelfOrganizingMapImpl implements SelfOrganizingMap {
    private static final Distance DEFAULT_DISTANCE = new EuclideanDistance();
    private double[][] weights;
    private Distance distance;

    public SelfOrganizingMapImpl(Distance distance, int inputNeurons, int outputNeurons) {
        if (distance == null) throw new IllegalArgumentException("distance can't be null");
        this.distance = distance;
        initWeights(inputNeurons, outputNeurons);
    }

    public SelfOrganizingMapImpl(int inputNeurons, int outputNeurons) {
        this(DEFAULT_DISTANCE, inputNeurons, outputNeurons);
    }

    @Override
    public void study(double[] input) {
        int winnerIndex = process(input);

    }

    @Override
    public int process(double[] input) {
        if (input.length != weights[0].length){
            throw new IllegalArgumentException("input vector has a wrong length");
        }

        int minIndex = 0;
        double minValue = distance.apply(weights[0], input);

        for (int i = 1; i < weights.length; i++) {
            double d = distance.apply(weights[i], input);
            if (d < minValue){
                minIndex = i;
                minValue = d;
            }
        }

        return minIndex;
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
