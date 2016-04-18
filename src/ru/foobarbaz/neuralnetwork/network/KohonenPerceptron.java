package ru.foobarbaz.neuralnetwork.network;

import ru.foobarbaz.neuralnetwork.SelfStudyingNetwork;

public class KohonenPerceptron extends AbstractPerceptron implements SelfStudyingNetwork {

    public KohonenPerceptron(int  inputNeurons, int outputNeurons) {
        super(new int[]{inputNeurons, outputNeurons});
    }

    @Override
    public void study(double[] input) {

    }
}
