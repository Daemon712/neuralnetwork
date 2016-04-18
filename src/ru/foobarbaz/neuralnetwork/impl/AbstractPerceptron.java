package ru.foobarbaz.neuralnetwork.impl;

import ru.foobarbaz.neuralnetwork.NeuralNetwork;

public abstract class AbstractPerceptron implements NeuralNetwork {
    protected double[][][] weights;
    protected double[][] neurons;

    protected AbstractPerceptron(int[] neuronsOnLayers) {
        if (neuronsOnLayers.length < 2) {
            throw new IllegalArgumentException("Requires two layers, at least");
        }

        initNeurons(neuronsOnLayers);
        initWeights(neuronsOnLayers);
    }

    @Override
    public double[] process(double[] input) {
        setInput(input);
        calculate();
        return getOutput();
    }

    @Override
    public abstract void study(double[] input, double[] expectedOutput);

    /**
     * Creates array of layers with neurons.
     * @param neuronsOnLayers array with numbers of neurons in layers
     */
    protected void initNeurons(int[] neuronsOnLayers){
        neurons = new double[neuronsOnLayers.length][];
        for (int i = 0; i < neuronsOnLayers.length; i++) {
            neurons[i] = new double[neuronsOnLayers[i]];
        }
    }

    /**
     * Creates weight for each pair of neurons in adjacent layers.
     * Weights are stored in arrays in next format:  weights[layer][next_layer_neuron][this_layer_neuron].
     * Fills all of weights by random values.
     * @param neuronsOnLayers array with numbers of neurons in layers
     */
    private void initWeights(int[] neuronsOnLayers){
        weights = new double[neuronsOnLayers.length - 1][][];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = new double[neuronsOnLayers[i+1]][];

            for (int j = 0; j < weights[i].length; j++) {

                weights[i][j] = new double[neuronsOnLayers[i]];
                for (int k = 0; k < weights[i][j].length; k++) {
                    weights[i][j][k] = Math.random() - 0.5;
                }
            }
        }
    }

    /**
     * Sets first (input) layer of network.
     * @param input array of input values. Values must must be between 0 and 1
     */
    private void setInput(double[] input){
        if (input.length != neurons[0].length) {
            throw new IllegalArgumentException("The number of input values must be equal to the number of neurons in the first layer");
        }

        for (double value : input) {
            if (value < 0 || value > 1) { //Input must be normalized
                throw new IllegalArgumentException("Input values must be between 0 and 1");
            }
        }
        neurons[0] = input.clone();
    }

    /**
     * Returns result of neural network's processing
     * @return copy of last layer of network
     */
    private double[] getOutput(){
        return neurons[neurons.length - 1].clone();
    }

    /**
     * Calculates all of neurons in the network
     */
    private void calculate(){
        for (int i = 1; i < neurons.length; i++) {
            for (int j = 0; j < neurons[i].length; j++) {
                calculateNeuron(i, j);
            }
        }
    }

    /**
     * Calculates one neuron. Value = sum()
     * @param layer neuron's layer. Can't be zero, because neurons of first layer would be filled by input values.
     * @param neuron neuron's position in this layer
     */
    private void calculateNeuron(int layer, int neuron){
        double sum = 0;

        for (int i = 0; i < neurons[layer-1].length; i++) {
            sum += neurons[layer-1][i] * weights[layer-1][neuron][i];
        }

        neurons[layer][neuron] = activate(sum);
    }

    /** Sigmoid function.
     * @param x value between -infinity and +infinity
     * @return value between 0 and 1
     */
    protected double activate(double x){
        return 1/(1+Math.exp(-x));
    }
}
