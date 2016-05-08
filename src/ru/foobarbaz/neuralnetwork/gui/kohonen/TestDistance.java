package ru.foobarbaz.neuralnetwork.gui.kohonen;

import ru.foobarbaz.neuralnetwork.distance.ChebyshevDistance;
import ru.foobarbaz.neuralnetwork.distance.EuclideanDistance;
import ru.foobarbaz.neuralnetwork.distance.ManhattanDistance;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.BiFunction;

public class TestDistance extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Distance");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        TestDistance testDistance = new TestDistance(800, 600);
        JButton button = new JButton("Generate New Points");
        button.addActionListener(e -> testDistance.resetPoints());
        JComboBox<BiFunction> functionsList = new JComboBox<>(new BiFunction[]{
                new EuclideanDistance(),
                new ManhattanDistance(),
                new ChebyshevDistance(),
        });
        functionsList.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            BiFunction function = (BiFunction)cb.getSelectedItem();
            testDistance.setDistanceFunction(function);
        });

        frame.add(button);
        frame.add(functionsList);
        frame.add(testDistance);
        frame.pack();
        frame.setVisible(true);
    }

    private final static int POINT_RADIUS = 3;
    private BufferedImage canvas;
    private Point[] points;
    private BiFunction<double[], double[], Double> distanceFunction;

    public TestDistance(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        initPoints();
        setDistanceFunction(new EuclideanDistance());
    }

    private void initPoints() {
        Random r = new Random();
        Color[] colors = new Color[]{
                Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA,
                Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY, Color.WHITE,
        };
        points = new Point[colors.length];
        for (int i = 0; i < colors.length; i++) {
            int x = POINT_RADIUS + r.nextInt(canvas.getWidth() - POINT_RADIUS);
            int y = POINT_RADIUS + r.nextInt(canvas.getHeight() - POINT_RADIUS);
            points[i] = new Point(x, y, colors[i]);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }

    public void setDistanceFunction(BiFunction<double[], double[], Double> distanceFunction) {
        this.distanceFunction = distanceFunction;
        drawCanvas();
        repaint();
    }

    public void resetPoints(){
        initPoints();
        drawCanvas();
        repaint();
    }

    private void drawCanvas(){
        for (int i = 0; i < canvas.getWidth(); i++) {
            for (int j = 0; j < canvas.getHeight(); j++) {
                double[] coordinates = new double[]{ i, j };
                Optional<Point> nearest = Arrays.stream(points).min((a, b) ->
                        Double.compare(
                                distanceFunction.apply(a.getCoordinates(), coordinates),
                                distanceFunction.apply(b.getCoordinates(), coordinates)
                        ));
                Color color = nearest.isPresent() ? nearest.get().getColor() : Color.BLACK;

                canvas.getRaster().setPixel(i, j, new float[]{color.getRed(), color.getGreen(), color.getBlue()});
            }
        }

        for (Point point : points) {
            for (int i = (int)point.getX() - POINT_RADIUS; i < point.getX() + POINT_RADIUS; i++) {
                for (int j = (int)point.getY() - POINT_RADIUS; j < point.getY() + POINT_RADIUS; j++) {
                    canvas.getRaster().setPixel(i, j, new float[]{0, 0, 0});
                }
            }
        }
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
