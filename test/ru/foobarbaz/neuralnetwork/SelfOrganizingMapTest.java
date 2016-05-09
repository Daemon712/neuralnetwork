package ru.foobarbaz.neuralnetwork;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMap;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMapImpl;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SelfOrganizingMapTest {
    private static SelfOrganizingMap selfOrganizingMap;
    private double[] input;

    @BeforeClass
    public static void setUp() throws Exception {
        selfOrganizingMap = new SelfOrganizingMapImpl(2, 4);
        for (int i = 0; i < 1000; i++) {
            for (Object[] params : data()) {
                double[] input = (double[]) params[0];
                selfOrganizingMap.study(input);
            }
        }
    }

    public SelfOrganizingMapTest(double[] input) {
        this.input = input;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{new double[]{ 0.0, 0.7 }},
                new Object[]{new double[]{ 0.7, 1.0 }},
                new Object[]{new double[]{ 0.3, 0.0 }},
                new Object[]{new double[]{ 1.0, 0.3 }}
        );
    }

    @Test
    public void process() throws Exception {
        int cluster1 = selfOrganizingMap.process(input);

        for (int i = 0; i < 5; i++) {
            double x = input[0] + (Math.random() - 0.5)/8;
            double y = input[0] + (Math.random() - 0.5)/8;
            int cluster2 = selfOrganizingMap.process(new double[]{x, y});
            Assert.assertEquals(cluster1, cluster2);
        }
    }
}