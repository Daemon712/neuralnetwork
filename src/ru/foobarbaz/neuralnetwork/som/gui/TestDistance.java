package ru.foobarbaz.neuralnetwork.som.gui;

import ru.foobarbaz.neuralnetwork.function.distance.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;

public class TestDistance extends JPanel {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Test Distance Functions");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.PAGE_AXIS));

        TestDistance testDistance = new TestDistance(800, 600);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setMinimum(1);
        spinnerModel.setMaximum(100);
        spinnerModel.setValue(colors.length);
        JSpinner spinner = new JSpinner(spinnerModel);
        spinner.setPreferredSize(new Dimension(50, 24));
        JButton button = new JButton("Generate New Points");
        button.addActionListener(e -> {
            Integer value = (Integer)spinner.getValue();
            testDistance.resetPoints(value == null ? 1 : value);
        });
        JComboBox<DistanceFunction> functionsList = new JComboBox<>(new DistanceFunction[]{
                new MinkowskiDistance(0.8),
                new ManhattanDistance(),
                new MinkowskiDistance(1.5),
                new EuclideanDistance(),
                new SquaredWeightedEuclideanDistance(0.01),
                new MinkowskiDistance(6),
                new ChebyshevDistance(),
        });
        functionsList.setSelectedIndex(3);
        functionsList.addActionListener(e -> {
            JComboBox cb = (JComboBox)e.getSource();
            DistanceFunction function = (DistanceFunction)cb.getSelectedItem();
            testDistance.setDistanceFunction(function);
        });

        panel.add(spinner);
        panel.add(button);
        panel.add(functionsList);
        frame.add(panel);
        frame.add(testDistance);
        frame.pack();
        frame.setVisible(true);
    }

    private static final int POINT_RADIUS = 3;
    private static final float[] POINT_COLOR = new float[]{0, 0, 0};
    private static final Color[] colors = new Color[]{
            Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN,
            Color.CYAN, Color.BLUE, Color.MAGENTA, Color.WHITE,
    };
    private BufferedImage canvas;
    private Point[] points;
    private DistanceFunction distanceFunction;

    public TestDistance(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        initPoints(colors.length);
        setDistanceFunction(new EuclideanDistance());
    }

    private void initPoints(int numOfPoints) {
        Random r = new Random();
        points = new Point[numOfPoints];
        for (int i = 0; i < points.length; i++) {
            int x = POINT_RADIUS + r.nextInt(canvas.getWidth() - 2 * POINT_RADIUS);
            int y = POINT_RADIUS + r.nextInt(canvas.getHeight() - 2 *POINT_RADIUS);
            points[i] = new Point(x, y, colors[i % colors.length]);
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

    public void setDistanceFunction(DistanceFunction distanceFunction) {
        this.distanceFunction = distanceFunction;
        drawCanvas();
        repaint();
    }

    public void resetPoints(int numOfPoints){
        initPoints(numOfPoints);
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
                Color color = Color.BLACK;
                if (nearest.isPresent()){
                    color = nearest.get().getColor();
                    double d = distanceFunction.apply(nearest.get().getCoordinates(), coordinates);
                    while (d > 50){
                        color = color.darker();
                        d -= 50;
                    }
                }

                canvas.getRaster().setPixel(i, j, new float[]{color.getRed(), color.getGreen(), color.getBlue()});
            }
        }

        for (Point point : points) {
            for (int i = (int)point.getX() - POINT_RADIUS; i < point.getX() + POINT_RADIUS; i++) {
                for (int j = (int)point.getY() - POINT_RADIUS; j < point.getY() + POINT_RADIUS; j++) {
                    canvas.getRaster().setPixel(i, j, POINT_COLOR);
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
