package ru.foobarbaz.neuralnetwork.perceptron.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DrawPixelsPanel extends JPanel {

    private CellPanel[][] cells;

    public DrawPixelsPanel(int width, int height) {
        cells = new CellPanel[height][width];
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                gbc.gridx = col;
                gbc.gridy = row;

                CellPanel cellPanel = initCell(width, height, row, col);
                add(cellPanel, gbc);
                cells[row][col] = cellPanel;
            }
        }
    }

    public boolean[][] getData(){
        boolean[][] data = new boolean[cells.length][cells[0].length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                data[i][j] = cells[i][j].isChecked();
            }
        }
        return data;
    }

    private CellPanel initCell(int width, int height, int row, int col) {
        CellPanel cellPanel = new CellPanel(col, row);
        Border border;
        if (row < height - 1) {
            if (col < width - 1) {
                border = new MatteBorder(2, 2, 0, 0, Color.GRAY);
            } else {
                border = new MatteBorder(2, 2, 0, 2, Color.GRAY);
            }
        } else {
            if (col < width - 1) {
                border = new MatteBorder(2, 2, 2, 0, Color.GRAY);
            } else {
                border = new MatteBorder(2, 2, 2, 2, Color.GRAY);
            }
        }
        cellPanel.setBorder(border);
        return cellPanel;
    }
}

class CellPanel extends JPanel{
    private int column, row;
    private boolean state;

    public CellPanel(int column, int row) {
        this.column = column;
        this.row = row;
        setState(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                 setState(!CellPanel.this.state);
            }
        });
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public boolean isChecked() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
        updateBackground();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(80, 80);
    }

    public void updateBackground() {
        setBackground(state ? Color.blue : Color.WHITE);
    }
}