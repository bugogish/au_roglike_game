package ru.spbau.mit.core.GUI;

import ru.spbau.mit.utils.Cell;

import java.io.IOException;

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

    public void draw() {
        try {
            TerminalGUI.addToTerminal(this);
        } catch (IOException e) {
            //TODO : WHERE AND HOW PROCESS THIS EXCEPTION
        }
    }

    public void redrawTo(Cell position) throws IOException {
        TerminalGUI.removeFromTerminal(this);
        this.currentPosition = position;
        TerminalGUI.addToTerminal(this);
    }

    public void clear() throws IOException {
        TerminalGUI.removeFromTerminal(this);
    }

    public char getIcon() {
        return icon;
    }
}
