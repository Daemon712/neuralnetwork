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
        int neurons = DIGITS[0].length;
        int bottleneck = (int)(Math.log(DIGITS.length) / Math.log(2));
        int hidden = bottleneck + (neurons - bottleneck) / 2;
        Perceptron perceptron = new PerceptronImpl(neurons, hidden, bottleneck, hidden, neurons);
        studyPerceptron(perceptron, eras);
        return perceptron;
    }

    public static void studyPerceptron(Perceptron perceptron, int eras){
        for (int i = 0; i < eras; i++) {
            for (double[] digit : DIGITS) {
                perceptron.study(digit, digit);
            }
        }
    }

    public static double[] getDigit(int digit){
        return DIGITS[digit].clone();
    }
}
