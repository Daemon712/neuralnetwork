package ru.foobarbaz.neuralnetwork.perceptron.gui;

import ru.foobarbaz.neuralnetwork.perceptron.logic.DigitAssociateTeacher;
import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;

import javax.swing.*;
import java.awt.*;

public class AssociationDemoApp extends JPanel{
    public AssociationDemoApp() {
        super(new GridLayout(1, 1));
        Perceptron perceptron = DigitAssociateTeacher.studyPerceptron(10000);
        JComponent processPanel = new AssociationDemoPanel(perceptron);
        add(processPanel);
    }

    public static void main(String[] args) {
        trySetLookAndFeel();
        JFrame frame = new JFrame("Neural Emulator of Associative Memory");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.add(new AssociationDemoApp(), BorderLayout.CENTER);
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
