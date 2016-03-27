package ru.foobarbaz.neuralnetwork.gui;

import ru.foobarbaz.neuralnetwork.NeuralNetwork;
import ru.foobarbaz.neuralnetwork.impl.Teacher;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private JTabbedPane tabbedPane;
    private NeuralNetwork neuralNetwork;

    public MainPanel() {
        super(new GridLayout(1, 1));
        initNetwork();
        tabbedPane = new JTabbedPane();

        //TODO
//        JComponent filePanel = null;
//        tabbedPane.addTab("File", null, filePanel, null);
//        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent processPanel = new ProcessPanel(neuralNetwork);
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
        Teacher teacher = new Teacher();
        teacher.study(1000);
        neuralNetwork = teacher.getNeuralNetwork();
    }
}
