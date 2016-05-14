package ru.foobarbaz.neuralnetwork.som.logic;

import ru.foobarbaz.neuralnetwork.function.GaussianFunction;
import ru.foobarbaz.neuralnetwork.function.HyperbolicFunction;
import ru.foobarbaz.neuralnetwork.function.distance.DistanceFunction;
import ru.foobarbaz.neuralnetwork.function.distance.EuclideanDistance;

import java.util.function.Function;

public class SelfOrganizingMapImpl implements SelfOrganizingMap {
    private static final DistanceFunction DEFAULT_DISTANCE_FUNCTION = new EuclideanDistance();
    private double[][] weights;
    private DistanceFunction distanceFunction;
    private Function<Double, Double> studyingSpeedFunction = new HyperbolicFunction(2, 1);
    private Function<Double, Double> neighborhoodFunction = new GaussianFunction();
    private int studyingEra = 0;

    public SelfOrganizingMapImpl(DistanceFunction distanceFunction, int inputNeurons, int outputNeurons) {
        if (distanceFunction == null) throw new IllegalArgumentException("distanceFunction can't be null");
        this.distanceFunction = distanceFunction;
        initWeights(inputNeurons, outputNeurons);
    }

    public SelfOrganizingMapImpl(int inputNeurons, int outputNeurons) {
        this(DEFAULT_DISTANCE_FUNCTION, inputNeurons, outputNeurons);
    }

    @Override
    public void study(double[] input) {
        int winnerIndex = process(input);

        for (double[] neuron : weights) {
            double distanceToWinner = distanceFunction.apply(neuron, weights[winnerIndex]);
            double delta = neighborhoodFunction.apply(distanceToWinner) *
                    studyingSpeedFunction.apply((double) studyingEra);

            for (int i = 0; i < neuron.length; i++) {
                neuron[i] += delta + (neuron[i] - input[i]);
            }
        }

        studyingEra++;
    }

    @Override
    public int process(double[] input) {
        if (input.length != weights[0].length){
            throw new IllegalArgumentException("input vector has a wrong length");
        }

        int minIndex = 0;
        double minValue = distanceFunction.apply(weights[0], input);

        for (int i = 1; i < weights.length; i++) {
            double d = distanceFunction.apply(weights[i], input);
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
