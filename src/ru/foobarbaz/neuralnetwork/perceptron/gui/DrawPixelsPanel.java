package ru.foobarbaz.neuralnetwork.perceptron.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class DrawPixelsPanel extends JPanel {
    private static final Color BORDER_COLOR = Color.gray;
    private static final Color FILL_COLOR = Color.blue;
    private static final Color EMPTY_COLOR = Color.white;
    private boolean readOnly;
    private Cell[] cells;

    public DrawPixelsPanel(int width, int height, boolean readOnly) {
        this.readOnly = readOnly;
        setLayout(new GridLayout(height, width));
        this.cells = new Cell[height * width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = initCell(width, height, row, col);
                add(cell);
                cells[row * width + col] = cell;
            }
        }
    }

    public DrawPixelsPanel(int width, int height) {
        this(width, height, false);
    }

    public double[] getData(){
        return Arrays.stream(cells).mapToDouble(Cell::getState).toArray();
    }

    public void setData(double[] data){
        for (int i = 0; i < data.length; i++) {
                cells[i].setState(data[i]);
        }
    }

    private Cell initCell(int width, int height, int row, int col) {
        Cell cell = new Cell();
        Border border;
        if (row < height - 1) {
            if (col < width - 1) {
                border = new MatteBorder(2, 2, 0, 0, BORDER_COLOR);
            } else {
                border = new MatteBorder(2, 2, 0, 2, BORDER_COLOR);
            }
        } else {
            if (col < width - 1) {
                border = new MatteBorder(2, 2, 2, 0, BORDER_COLOR);
            } else {
                border = new MatteBorder(2, 2, 2, 2, BORDER_COLOR);
            }
        }
        cell.setBorder(border);
        return cell;
    }

    class Cell extends JPanel{
        private double state;

        public Cell() {
            setState(0);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (!readOnly) setState(1 - Cell.this.state);
                }
            });
        }

        public double getState() {
            return state;
        }

        public void setState(double state) {
            this.state = state;
            if (state < 0 || state > 1) throw new IllegalArgumentException();

            Color color = new Color(
                    (int) (EMPTY_COLOR.getRed() + state * (FILL_COLOR.getRed() - EMPTY_COLOR.getRed())),
                    (int) (EMPTY_COLOR.getGreen() + state * (FILL_COLOR.getGreen() - EMPTY_COLOR.getGreen())),
                    (int) (EMPTY_COLOR.getBlue() + state * (FILL_COLOR.getBlue() - EMPTY_COLOR.getBlue()))
            );
            setBackground(color);
        }
    }
}