package ru.foobarbaz.neuralnetwork.network;

public class Perceptron extends AbstractPerceptron implements StudyingWithTeacherNetwork {
    private static final double STUDYING_POWER = 3;
    private double[][] errors;

    public Perceptron(int[] neuronsOnLayers){
        super(neuronsOnLayers);
        initErrors(neuronsOnLayers);
    }

    @Override
    public void study(double[] input, double[] expectedOutput) {
        if (input.length != neurons[0].length) {
            throw new IllegalArgumentException("The number of input values must be equal to the number of neurons in the first layer");
        }
        if (expectedOutput.length != neurons[neurons.length - 1].length) {
            throw new IllegalArgumentException("The number of output values must be equal to the number of neurons in the last layer");
        }
        double[] actualOutput = process(input);

        double[] errors = new double[actualOutput.length];
        for (int i = 0; i < errors.length; i++) {
            errors[i] =(expectedOutput[i]- actualOutput[i])*getDerValues(2,i);
            this.errors[2][i]=errors[i];
            double[] inputLinks=weights[1][i];
            for(int j=0;j<inputLinks.length;j++){
                double weightDelta=errors[i]*neurons[1][j]* STUDYING_POWER;
                weights[1][i][j]=weights[1][i][j]+weightDelta;
            }
        }
        double[] middleLayer=neurons[1];
        for(int i=0;i<middleLayer.length;i++){
            double errorSum=0;

            for(int j=0;j<neurons[2].length;j++){
                errorSum += weights[1][j][i]*this.errors[2][j];
            }
            double error=errorSum*getDerValues(1,i);
            for(int j=0; j<neurons[0].length;j++){
                double weightDelta=error*neurons[0][j] * STUDYING_POWER;
                weights[0][i][j]=weights[0][i][j]+weightDelta;
            }
        }
    }

    private double getDerValues(int layer, int neuron){
        double sum=0;
        double[] inputLinks=weights[layer-1][neuron];
        for(int i=0;i<inputLinks.length;i++){
            sum+=inputLinks[i]*neurons[layer-1][i];
        }
        return derValue(sum);
    }

    private double derValue(double value){
        double func = activate(value);
        return func*(1-func);
    }

    private void initErrors(int[] neuronsOnLayers){
        errors = new double[neuronsOnLayers.length][];
        for (int i = 0; i < neuronsOnLayers.length; i++) {
            errors[i] = new double[neuronsOnLayers[i]];
        }
    }
}
