package ru.foobarbaz.neuralnetwork;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;
import ru.foobarbaz.neuralnetwork.perceptron.logic.PerceptronImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class AssociativeMemorySimpleTest {
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{new double[]{ 1, 0, 0, 0, 0, 0, 0, 0 }},
                new Object[]{new double[]{ 0, 1, 0, 0, 0, 0, 0, 0 }},
                new Object[]{new double[]{ 0, 0, 1, 0, 0, 0, 0, 0 }},
                new Object[]{new double[]{ 0, 0, 0, 1, 0, 0, 0, 0 }},
                new Object[]{new double[]{ 0, 0, 0, 0, 1, 0, 0, 0 }},
                new Object[]{new double[]{ 0, 0, 0, 0, 0, 1, 0, 0 }},
                new Object[]{new double[]{ 0, 0, 0, 0, 0, 0, 1, 0 }},
                new Object[]{new double[]{ 0, 0, 0, 0, 0, 0, 0, 1 }}
        );
    }

    private static Perceptron perceptron;

    @BeforeClass
    public static void setUp() throws Exception {
        perceptron = new PerceptronImpl(8, 5, 3, 5, 8);
        for (int i = 0; i < 1000; i++) {
            for (Object[] objects : data()) {
                double[] d = (double[]) objects[0];
                perceptron.study(d, d);
            }
        }
    }

    private double[] data;

    public AssociativeMemorySimpleTest(double[] data) {
        this.data = data;
    }

    @Test
    public void testProcess() throws Exception {
        double[] result = perceptron.process(data);
        System.out.println("Input: " + Arrays.toString(data));
        System.out.println("Output: " + Arrays.toString(result));
        for (int i = 0; i < result.length; i++) {
            Assert.assertTrue(Math.abs(data[i] - result[i]) < 0.01);
        }
    }
}
