package ru.spbau.mit.core.GUI;

import com.googlecode.lanterna.TerminalPosition;

import java.io.IOException;

public abstract class Drawable {
    private TerminalPosition current;
    private final char icon;

    public Drawable(char icon, TerminalPosition current) {
        this.icon = icon;
        this.current = current;
    }

    public TerminalPosition getCurrentPosition() {
        return current;
    }

    public void setCurrentPosition(TerminalPosition current) {
        this.current = current;
    }

    public void draw() {
        try {
            TerminalGUI.addToTerminal(this);
        } catch (IOException e) {
            //TODO : WHERE AND HOW PROCESS THIS EXCEPTION
        }
    }

    public void redrawTo(TerminalPosition position) throws IOException {
        TerminalGUI.removeFromTerminal(this);
        this.current = position;
        TerminalGUI.addToTerminal(this);
    }

    public void clear() throws IOException {
        TerminalGUI.removeFromTerminal(this);
    }

    public char getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drawable drawable = (Drawable) o;

        return current.equals(drawable.current);
    }

    @Override
    public int hashCode() {
        int result = current.hashCode();
        result = 31 * result;
        return result;
    }
}