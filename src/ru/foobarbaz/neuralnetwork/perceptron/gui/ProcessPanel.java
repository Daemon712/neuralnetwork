package ru.foobarbaz.neuralnetwork.perceptron.gui;

import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class ProcessPanel extends JPanel implements ActionListener {

    private JLabel outputLabel;
    private DrawPixelsPanel drawPixelsPanel;
    private Perceptron perceptron;

    public ProcessPanel(Perceptron perceptron) {
        this.perceptron = perceptron;

        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

        JPanel leftPanel = initLeftPanel();
        JPanel rightPanel = initRightPanel();

        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(leftPanel);
        add(Box.createRigidArea(new Dimension(10,0)));
        add(rightPanel);
    }

    private JPanel initRightPanel() {
        initOutputLabel();
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Output"),
                BorderFactory.createEmptyBorder(10,10,10,10)));
        rightPanel.add(outputLabel);
        return rightPanel;
    }

    private void initOutputLabel() {
        outputLabel = new JLabel() {
            public Dimension getPreferredSize() {
                return new Dimension(300, 400);
            }

            public Dimension getMinimumSize() {
                return new Dimension(200, 200);
            }

            public Dimension getMaximumSize() {
                return new Dimension(1000, 1080);
            }
        };
        outputLabel.setVerticalAlignment(SwingConstants.CENTER);
        outputLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private JPanel initLeftPanel() {
        JButton processButton = initProcessButton();

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Input"),
                BorderFactory.createEmptyBorder(10,10,10,10)));

        drawPixelsPanel = new DrawPixelsPanel(3, 5);
        leftPanel.add(drawPixelsPanel);
        leftPanel.add(Box.createRigidArea(new Dimension(0,10)));
        leftPanel.add(processButton);
        return leftPanel;
    }

    private JButton initProcessButton() {
        JButton processButton = new JButton("Process");
        processButton.setMnemonic(KeyEvent.VK_C);
        processButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        processButton.addActionListener(this);
        return processButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double[] input = drawPixelsPanel.getData();
        double[] output = perceptron.process(input);
        outputLabel.setText(formatOutput(output));
    }

    private String formatOutput(double[] output){
        StringBuilder builder = new StringBuilder( "<html><font size=+4");
        DecimalFormat df = new DecimalFormat("0.0000");
        for (int i = 0; i < output.length; i++) {
            String color = output[i] > 0.8 ? "green" :
                    output[i] < 0.2 ? "gray" : "black";
            builder
                .append("<font color=").append(color).append("><p>")
                .append("<b>").append(i).append(":</b> ")
                .append(df.format(output[i]))
                .append("</p><font>");
        }

        builder.append("</font></html>");
        return builder.toString();
    }
}
