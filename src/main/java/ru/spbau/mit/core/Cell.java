package ru.spbau.mit.core;

import com.googlecode.lanterna.TerminalPosition;

/**
 * Class for denoting a position on map
 */

public class Cell extends TerminalPosition {
    public Cell(int col, int row) {
        super(col, row);
    }
}
