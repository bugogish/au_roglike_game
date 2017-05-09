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

    public void setCurrentPosition(Cell current) {
        this.currentPosition = current;
    }

    /**
     * shows an object on screen
     */
    public void draw() {
        TerminalGUI.addToTerminal(this);
    }

    /**
     * changes object's position and shows in on screen in new place
     */
    public void redrawTo(Cell position) {
        TerminalGUI.removeFromTerminal(this);
        this.currentPosition = position;
        TerminalGUI.addToTerminal(this);
    }

    /**
     * removes object from screen
     */
    public void clear() {
        TerminalGUI.removeFromTerminal(this);
    }

    public char getIcon() {
        return icon;
    }
}
