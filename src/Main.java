import ru.foobarbaz.neuralnetwork.gui.MainPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

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
