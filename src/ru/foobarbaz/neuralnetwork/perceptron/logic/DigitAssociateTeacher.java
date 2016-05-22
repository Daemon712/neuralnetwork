package ru.foobarbaz.neuralnetwork.perceptron.logic;

public class DigitAssociateTeacher {
    private static final double[][] DIGITS = new double[][]{
            new double[]{
                    0,1,1,1,0,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,0
            },
            new double[]{
                    0,0,0,0,1,
                    0,0,0,1,1,
                    0,0,1,0,1,
                    0,0,0,0,1,
                    0,0,0,0,1,
                    0,0,0,0,1,
                    0,0,0,0,1
            },
            new double[]{
                    0,1,1,1,0,
                    1,0,0,0,1,
                    0,0,0,0,1,
                    0,0,1,1,0,
                    0,1,0,0,0,
                    1,0,0,0,0,
                    1,1,1,1,1
            },
            new double[]{
                    0,1,1,1,0,
                    1,0,0,0,1,
                    0,0,0,0,1,
                    0,0,1,1,0,
                    0,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,0
            },
            new double[]{
                    1,0,0,0,1,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    1,1,1,1,1,
                    0,0,0,0,1,
                    0,0,0,0,1,
                    0,0,0,0,1
            },
            new double[]{
                    1,1,1,1,1,
                    1,0,0,0,0,
                    1,0,0,0,0,
                    0,1,1,1,0,
                    0,0,0,0,1,
                    0,0,0,0,1,
                    1,1,1,1,0
            },
            new double[]{
                    0,1,1,1,0,
                    1,0,0,0,1,
                    1,0,0,0,0,
                    1,1,1,1,0,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,0
            },
            new double[]{
                    1,1,1,1,1,
                    0,0,0,0,1,
                    0,0,0,0,1,
                    0,0,0,1,0,
                    0,0,1,0,0,
                    0,1,0,0,0,
                    1,0,0,0,0
            },
            new double[]{
                    0,1,1,1,0,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,0,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,0
            },
            new double[]{
                    0,1,1,1,0,
                    1,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,1,
                    0,0,0,0,1,
                    1,0,0,0,1,
                    0,1,1,1,0
            },
    };

    public static Perceptron studyPerceptron(int eras){
        Perceptron perceptron = new PerceptronImpl(35, 25, 15, 5, 15, 25, 35);
        for (int i = 0; i < eras; i++) {
            for (double[] digit : DIGITS) {
                perceptron.study(digit, digit);
            }
        }
        return perceptron;
    }

    public static double[] getDigit(int digit){
        return DIGITS[digit].clone();
    }
}
