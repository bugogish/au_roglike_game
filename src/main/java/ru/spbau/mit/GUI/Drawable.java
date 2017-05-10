package ru.spbau.mit.GUI;

import ru.spbau.mit.core.Cell;

/**
 * Class for representing an object that can be shown on certain position on GUI
 */

public abstract class Drawable {
    private Cell currentPosition;
    private final char icon;

    public Drawable(char icon, Cell currentPosition) {
        this.icon = icon;
        this.currentPosition = currentPosition;
    }

    public Cell getCurrentPosition() {
        return currentPosition;
    }

    protected void setCurrentPosition(Cell current) {
        this.currentPosition = current;
    }

    public char getIcon() {
        return icon;
    }
}
