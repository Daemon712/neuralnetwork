package ru.foobarbaz.neuralnetwork.impl;

import ru.foobarbaz.neuralnetwork.NeuralNetwork;

public class Teacher {


    private double[][] inputSet;
    private double[][] exceptedSet;
    public Teacher(){
        neuralNetwork=new Perceptron(new int[]{15,12,10});
        initSets();

    }
    public void setUp(){
        initInputSet();
        initExceptedSet();
        for(int i=0;i<10000;i++){
            for(int j=0;j<inputSet.length;j++)
            {
                neuralNetwork.study(inputSet[j],exceptedSet[j]);
            }
        }
    }
    private void initSets(){
        inputSet = new double[10][];
        exceptedSet =new double[10][];
        for (int i = 0; i < 10; i++) {
            inputSet[i] = new double[15];
            exceptedSet[i]= new double[10];
        }
    }
    private void initInputSet(){
        inputSet[1]=new double[]{
                0,0,1,
                0,1,1,
                0,0,1,
                0,0,1,
                0,0,1};
        inputSet[2]=new double[]{
                1,1,1,
                0,0,1,
                0,1,0,
                1,0,0,
                1,1,1};
        inputSet[3]=new double[]{
                1,1,1,
                0,0,1,
                1,1,1,
                0,0,1,
                1,1,1};
        inputSet[4]=new double[]{
                1,0,1,
                1,0,1,
                1,1,1,
                0,0,1,
                0,0,1};
        inputSet[5]=new double[]{
                1,1,1,
                1,0,0,
                1,1,1,
                0,0,1,
                1,1,1};
        inputSet[6]=new double[]{
                1,1,1,
                1,0,0,
                1,1,1,
                1,0,1,
                1,1,1};
        inputSet[7]=new double[]{
                1,1,1,
                0,0,1,
                0,0,1,
                0,1,0,
                0,1,0};
        inputSet[8]=new double[]{
                1,1,1,
                1,0,1,
                1,1,1,
                1,0,1,
                1,1,1};
        inputSet[9]=new double[]{
                1,1,1,
                1,0,1,
                1,1,1,
                0,0,1,
                1,1,1};
        inputSet[0]=new double[]{
                1,1,1,
                1,0,1,
                1,0,1,
                1,0,1,
                1,1,1};
    }
    private void initExceptedSet(){
        exceptedSet[0]=new double[]{1,0,0,0,0,0,0,0,0,0};
        exceptedSet[1]=new double[]{0,1,0,0,0,0,0,0,0,0};
        exceptedSet[2]=new double[]{0,0,1,0,0,0,0,0,0,0};
        exceptedSet[3]=new double[]{0,0,0,1,0,0,0,0,0,0};
        exceptedSet[4]=new double[]{0,0,0,0,1,0,0,0,0,0};
        exceptedSet[5]=new double[]{0,0,0,0,0,1,0,0,0,0};
        exceptedSet[6]=new double[]{0,0,0,0,0,0,1,0,0,0};
        exceptedSet[7]=new double[]{0,0,0,0,0,0,0,1,0,0};
        exceptedSet[8]=new double[]{0,0,0,0,0,0,0,0,1,0};
        exceptedSet[9]=new double[]{0,0,0,0,0,0,0,0,0,1};
    }
    public NeuralNetwork getNeuralNetwork() {
        return neuralNetwork;
    }

    private NeuralNetwork neuralNetwork;

    public double[][] getInputSet() {
        return inputSet;
    }

    public double[][] getExceptedSet() {
        return exceptedSet;
    }

}
