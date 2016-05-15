package ru.foobarbaz.neuralnetwork;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMapImpl;

import java.util.HashSet;
import java.util.Set;

public class SelfOrganizingMapTest {
    private static SelfOrganizingMapImpl selfOrganizingMap;
    private static final int CLUSTERS = 4;
    private static final int ERAS = 10;
    public static double[][] studyingDataSet = new double[][]{
            { -1.0,    0.5 },
            {  0.5,    1.0 },
            { -0.5,   -1.0 },
            {  1.0,   -0.5 }
    };

    @BeforeClass
    public static void setUp() throws Exception {
        selfOrganizingMap = new SelfOrganizingMapImpl(2, CLUSTERS);
        selfOrganizingMap.study(studyingDataSet, ERAS);
    }

    @Test
    public void testClusters() {
        Set<Integer> clusters = new HashSet<>(CLUSTERS);
        for (double[] point : studyingDataSet) {
            int cluster = selfOrganizingMap.process(point);
            Assert.assertTrue(clusters.add(cluster));
        }
    }

    @Test
    public void testNeighborhood() {
        for (double[] point : studyingDataSet) {
            int cluster1 = selfOrganizingMap.process(point);
            for (int i = 0; i < 5; i++) {
                double x = point[0] + (Math.random() - 0.5)/4;
                double y = point[1] + (Math.random() - 0.5)/4;
                int cluster2 = selfOrganizingMap.process(new double[]{x, y});
                Assert.assertEquals(cluster1, cluster2);
            }
        }
    }
}