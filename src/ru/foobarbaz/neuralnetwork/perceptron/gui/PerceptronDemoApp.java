package ru.foobarbaz.neuralnetwork.perceptron.gui;

import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;
import ru.foobarbaz.neuralnetwork.perceptron.logic.DigitTeacher;

import javax.swing.*;
import java.awt.*;

public class PerceptronDemoApp extends JPanel {
    private Perceptron perceptron;

    public PerceptronDemoApp() {
        super(new GridLayout(1, 1));
        initNetwork();
        JComponent processPanel = new PerceptronDemoPanel(perceptron);
        add(processPanel);
        setPreferredSize(new Dimension(800, 600));
    }

    private void initNetwork(){
        DigitTeacher teacher = new DigitTeacher();
        teacher.study(10000);
        perceptron = teacher.getPerceptron();
    }

    public static void main(String[] args) {
        trySetLookAndFeel();
        JFrame frame = new JFrame("Neural Network");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new PerceptronDemoApp(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }

    private static void trySetLookAndFeel(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getClass().getSimpleName() + " " + e.getMessage());
        }
    }
}
