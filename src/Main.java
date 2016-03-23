import ru.foobarbaz.neuralnetwork.NeuralNetwork;
import ru.foobarbaz.neuralnetwork.impl.Perceptron;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        NeuralNetwork nn = new Perceptron(new int[]{5, 4, 3});
        double[] res = nn.process(new double[]{1,0,1,1,0});
        System.out.println(Arrays.toString(res));
    }
}
