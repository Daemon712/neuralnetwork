package ru.foobarbaz.neuralnetwork.perceptron;

import ru.foobarbaz.neuralnetwork.perceptron.gui.DrawPixelsPanel;
import ru.foobarbaz.neuralnetwork.perceptron.logic.DigitAssociateTeacher;
import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;
import ru.foobarbaz.neuralnetwork.perceptron.logic.PerceptronImpl;

import javax.swing.*;
import java.awt.*;

public class VisualTestAssociativeMemory extends JPanel {
    private static final int ERAS = 100;
    private static final int VIEW_WIDTH = 5;
    private static final int VIEW_HEIGHT = 7;
    private static final int DIGITS = 10;

    private DrawPixelsPanel[] panels;
    private double[][][] data;

    public VisualTestAssociativeMemory() {
        super(new GridBagLayout());
        initGrid();
        initData();
        initSlider();
    }

    private void initData() {
        int neurons = VIEW_HEIGHT * VIEW_WIDTH;
        data = new double[ERAS + 1][DIGITS][neurons];
        Perceptron perceptron = new PerceptronImpl(neurons, 20, 3, 20, neurons);

        for (int j = 0; j < DIGITS; j++) {
            data[0][j] = perceptron.process(DigitAssociateTeacher.getDigit(j));
            panels[j].setData(data[0][j]);
        }

        for (int i = 0; i < ERAS; i++) {
            DigitAssociateTeacher.studyPerceptron(perceptron, 100);
            for (int j = 0; j < DIGITS; j++) {
                data[i + 1][j] = perceptron.process(DigitAssociateTeacher.getDigit(j));
            }
        }
    }

    private void initGrid(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1;
        constraints.weighty = 1;
        constraints.insets = new Insets(10,10,10,10);
        panels = new DrawPixelsPanel[DIGITS];
        for (int i = 0; i < DIGITS; i++) {
            panels[i] = new DrawPixelsPanel(VIEW_WIDTH, VIEW_HEIGHT, true);
            constraints.gridy = i / (DIGITS / 2);
            add(panels[i], constraints);
        }
    }

    private void initSlider(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridy = 3;
        constraints.gridwidth = 5;
        constraints.weightx = 1;
        constraints.weighty = 0.1;
        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, ERAS, 0);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(e -> {
            int v = slider.getValue();
            for (int i = 0; i < panels.length; i++) {
                panels[i].setData(data[v][i]);
            }
        });
        add(slider, constraints);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Visual Test of Associative Memory");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new VisualTestAssociativeMemory(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
