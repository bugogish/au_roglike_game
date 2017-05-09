package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.GUI.TerminalGUI;

public class Cell extends TerminalPosition {
    public Cell(int col, int row) {
        super(getAcceptableColumn(col), getAcceptableRow(row));
    }

    private static int getAcceptableRow(int row) {
        return Math.min(Math.max(0, row), TerminalGUI.getMaxRow() - 1);
    }

    private static int getAcceptableColumn(int col) {
        return Math.min(Math.max(0, col), TerminalGUI.getMaxColumn() - 1);
    }
}
