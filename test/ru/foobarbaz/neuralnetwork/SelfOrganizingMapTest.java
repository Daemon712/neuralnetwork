package ru.foobarbaz.neuralnetwork;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.foobarbaz.neuralnetwork.function.distance.DistanceFunction;
import ru.foobarbaz.neuralnetwork.function.distance.EuclideanDistance;
import ru.foobarbaz.neuralnetwork.som.logic.SelfOrganizingMapImpl;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

@RunWith(Parameterized.class)
public class SelfOrganizingMapTest {
    private static SelfOrganizingMapImpl selfOrganizingMap;
    private double[] input;

    @BeforeClass
    public static void setUp() throws Exception {
        selfOrganizingMap = new SelfOrganizingMapImpl(2, 4);
        drawToFile(drawCanvas(), "temp/_start.png");

        for (int i = 0; i < 5; i++) {
            selfOrganizingMap.setStudyingEra(i);
            int j = 0;
            for (Object[] params : data()) {
                double[] input = (double[]) params[0];
                selfOrganizingMap.study(input);
                drawToFile(drawCanvas(), String.format("temp/era%d_study%d.png", i, j++));
            }

        }

        Arrays.stream(selfOrganizingMap.getWeights()).map(Arrays::toString).forEach(System.out::println);
    }

    private static void drawToFile(BufferedImage image, String filePath) throws IOException{
        File file = new File(filePath);
        if (!file.exists()) file.createNewFile();
        ImageIO.write(image, "png", file);
    };

    public SelfOrganizingMapTest(double[] input) {
        this.input = input;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(
                new Object[]{new double[]{ -1.0,    0.5 }},
                new Object[]{new double[]{  0.5,    1.0 }},
                new Object[]{new double[]{ -0.5,   -1.0 }},
                new Object[]{new double[]{  1.0,   -0.5 }}
        );
    }

    @Test
    public void testOne() throws Exception {
        int cluster = selfOrganizingMap.process(input);
        System.out.println(cluster);
        Assert.assertTrue(cluster >= 0);
    }

    @Test
    public void testNeighborhood() throws Exception {
        int cluster1 = selfOrganizingMap.process(input);
        for (int i = 0; i < 5; i++) {
            double x = input[0] + (Math.random() - 0.5)/4;
            double y = input[1] + (Math.random() - 0.5)/4;
            int cluster2 = selfOrganizingMap.process(new double[]{x, y});
            Assert.assertEquals(cluster1, cluster2);
        }
    }

    private static BufferedImage drawCanvas(){
        BufferedImage canvas = new BufferedImage(200, 200,  BufferedImage.TYPE_INT_RGB);
        Color[] colors = new Color[]{ Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW };
        double[][] weights = selfOrganizingMap.getWeights();
        Point[] points = new Point[weights.length];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(100 + 50 * weights[i][0], 100 + 50 * weights[i][1], colors[i]);
        }
        DistanceFunction distanceFunction = new EuclideanDistance();

        for (int i = 0; i < canvas.getWidth(); i++) {
            for (int j = 0; j < canvas.getHeight(); j++) {
                double[] coordinates = new double[]{ i, j };
                Optional<SelfOrganizingMapTest.Point> nearest = Arrays.stream(points).min((a, b) ->
                        Double.compare(
                                distanceFunction.apply(a.getCoordinates(), coordinates),
                                distanceFunction.apply(b.getCoordinates(), coordinates)
                        ));
                Color color = nearest.isPresent() ? nearest.get().getColor() : Color.BLACK;
                canvas.getRaster().setPixel(i, j, new float[]{color.getRed(), color.getGreen(), color.getBlue()});
            }
        }


        for (Object[] objects : data()) {
            int pointRadius = 3;
            double[] point = (double[])objects[0];
            int x = (int)(100 + 50 * point[0]);
            int y = (int)(100 + 50 * point[1]);
            for (int i = x - pointRadius; i < x + pointRadius; i++) {
                for (int j = y - pointRadius; j < y + pointRadius; j++) {
                    if (i > 0 && i < 200 && j > 0 && j < 200){
                        canvas.getRaster().setPixel(i, j, new float[]{255, 255, 255});
                    }
                }
            }
        }

        for (SelfOrganizingMapTest.Point point : points) {
            int pointRadius = 2;
            for (int i = (int)point.getX() - pointRadius; i < point.getX() + pointRadius; i++) {
                for (int j = (int)point.getY() - pointRadius; j < point.getY() + pointRadius; j++) {
                    if (i > 0 && i < 200 && j > 0 && j < 200){
                        canvas.getRaster().setPixel(i, j, new float[]{0, 0, 0});
                    }
                }
            }
        }
        return canvas;
    }

    private static class Point {
        private double x, y;
        private Color color;

        public Point(double x, double y, Color color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }

        public Color getColor() {
            return color;
        }

        public double getY() {
            return y;
        }

        public double getX() {
            return x;
        }

        public double[] getCoordinates(){
            return new double[]{ x, y };
        }
    }
}