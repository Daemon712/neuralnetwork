package ru.foobarbaz.neuralnetwork.perceptron.gui;

import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class AssociationDemoPanel extends JPanel {
    private Perceptron perceptron;
    private DrawPixelsPanel inputControl, outputControl;
    private JLabel compressLabel;

    public AssociationDemoPanel(Perceptron perceptron) {
        this.perceptron = perceptron;
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(initInputPanel());
        add(Box.createRigidArea(new Dimension(10,0)));
        add(initCompressPanel());
        add(Box.createRigidArea(new Dimension(10,0)));
        add(initOutputPanel());
    }

    private JPanel initCompressPanel() {
        JPanel leftPanel = createBoxPanel("Compress");
        compressLabel = new JLabel();
        compressLabel.setPreferredSize(new Dimension(300, 1000));
        leftPanel.add(compressLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftPanel.add(initProcessButton());
        return leftPanel;
    }

    private JButton initProcessButton() {
        JButton processButton = new JButton("Process");
        processButton.setMnemonic(KeyEvent.VK_C);
        processButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        processButton.addActionListener((e) -> {
            double[] input = inputControl.getData();
            double[] output = perceptron.process(input);
            double[] compress = perceptron.getNeurons(perceptron.getLayers()/2);
            compressLabel.setText(formatCompress(compress));
            outputControl.setData(output);
        });
        return processButton;
    }

    private String formatCompress(double[] compress) {
        StringBuilder builder = new StringBuilder( "<html><font size=+2>");
        DecimalFormat df = new DecimalFormat("0.0000");
        for (double number : compress) {
            builder
                .append("<font color=")
                .append(number > 0.8 ? "black" : "gray")
                .append("><p>")
                .append(df.format(number))
                .append("</p></font>");
        }

        builder.append("</font></html>");
        return builder.toString();
    }

    private JPanel initOutputPanel() {
        JPanel panel = createBoxPanel("Output");
        panel.setPreferredSize(new Dimension(800, 1000));
        outputControl = new DrawPixelsPanel(5, 7, true);
        panel.add(outputControl);
        return panel;
    }

    private JPanel initInputPanel() {
        JPanel panel = createBoxPanel("Input");
        panel.setPreferredSize(new Dimension(800, 1000));
        inputControl = new DrawPixelsPanel(5, 7, false);
        panel.add(inputControl);
        return panel;
    }

    private JPanel createBoxPanel(String title) {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(title),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        return leftPanel;
    }
}
