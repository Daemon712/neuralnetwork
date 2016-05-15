package ru.foobarbaz.neuralnetwork.som.logic;

import ru.foobarbaz.neuralnetwork.function.GaussianFunction;
import ru.foobarbaz.neuralnetwork.function.HyperbolicFunction;
import ru.foobarbaz.neuralnetwork.function.distance.DistanceFunction;
import ru.foobarbaz.neuralnetwork.function.distance.EuclideanDistance;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SelfOrganizingMapImpl implements SelfOrganizingMap {
    private static final DistanceFunction DEFAULT_DISTANCE_FUNCTION = new EuclideanDistance();
    private double potentialMinimum = 1;
    private int inputParams;
    private List<Neuron> neurons;
    private DistanceFunction distanceFunction;
    private Function<Double, Double> studyingSpeedFunction = new HyperbolicFunction(1, 1);
    private Function<Double, Double> neighborhoodFunction = new GaussianFunction(0.5, 0);
    private int studyingEra = 0;

    public SelfOrganizingMapImpl(DistanceFunction distanceFunction, int inputParams, int clusters) {
        if (distanceFunction == null) throw new IllegalArgumentException("distanceFunction can't be null");
        if (inputParams < 1) throw new IllegalArgumentException("inputParams must be positive");
        if (clusters < 1) throw new IllegalArgumentException("clusters must be positive");
        this.distanceFunction = distanceFunction;
        this.inputParams = inputParams;
        neurons = new ArrayList<>(clusters);
        for (int i = 0; i < clusters; i++) {
            neurons.add(new Neuron(i));
        }
    }

    public SelfOrganizingMapImpl(int inputNeurons, int outputNeurons) {
        this(DEFAULT_DISTANCE_FUNCTION, inputNeurons, outputNeurons);
    }

    @Override
    public void study(double[] input) {
        Neuron winner = getWinner(input, true);

        neurons.forEach(neuron -> {
            double distanceToWinner = distanceFunction.apply(neuron.weights, winner.weights);
            double power = neighborhoodFunction.apply(distanceToWinner) *
                    studyingSpeedFunction.apply((double) studyingEra);

            for (int i = 0; i < neuron.weights.length; i++) {
                double delta = input[i] - neuron.weights[i];
                neuron.weights[i] += power * delta;
            }

            neuron.potential += 1d / neurons.size();
            if (winner.index == neuron.index)
                neuron.potential -= potentialMinimum;
        });
    }

    public void setStudyingEra(int studyingEra) {
        if (studyingEra < 0) throw new IllegalArgumentException();
        this.studyingEra = studyingEra;
    }

    @Override
    public int process(double[] input) {
        return getWinner(input, false).index;
    }

    private Neuron getWinner(double[] input, boolean filterPotentials) {
        if (input.length != inputParams){
            throw new IllegalArgumentException("input vector has a wrong length");
        }
        return neurons.stream()
                .filter(n -> filterPotentials || n.potential >= potentialMinimum)
                .min((a, b) -> Double.compare(
                    distanceFunction.apply(a.weights, input),
                    distanceFunction.apply(b.weights, input)
                )
        ).get();
    }

    @Override
    public double[][] getWeights() {
        double[][] weights = new double[neurons.size()][];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = neurons.get(i).weights.clone();
        }
        return weights;
    }

    private class Neuron {
        private final int index;
        private double potential;
        private double[] weights;

        public Neuron(int index) {
            this.index = index;
            this.potential = 1;
            weights = new double[inputParams];
            for (int i = 0; i < weights.length; i++) {
                weights[i] = Math.random() - 0.5;
            }
        }
    }
}
