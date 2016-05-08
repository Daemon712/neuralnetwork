package ru.foobarbaz.neuralnetwork.perceptron.gui;

import ru.foobarbaz.neuralnetwork.perceptron.logic.Perceptron;
import ru.foobarbaz.neuralnetwork.perceptron.logic.DigitTeacher;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private Perceptron perceptron;

    public MainPanel() {
        super(new GridLayout(1, 1));
        initNetwork();
        tabbedPane = new JTabbedPane();

        //TODO
//        JComponent filePanel = null;
//        tabbedPane.addTab("File", null, filePanel, null);
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent processPanel = new ProcessPanel(perceptron);
        tabbedPane.addTab("Process", null, processPanel, null);

        //TODO
//        JComponent studyPanel = null;
//        tabbedPane.addTab("Study", null, studyPanel, null);
//        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane);
        setPreferredSize(new Dimension(800, 600));
    }

    private void initNetwork(){
        DigitTeacher teacher = new DigitTeacher();
        teacher.study(1000);
        perceptron = teacher.getPerceptron();
    }

    public static void main(String[] args) {
        trySetLookAndFeel();
        JFrame frame = new JFrame("Neural Network");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new MainPanel(), BorderLayout.CENTER);
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
