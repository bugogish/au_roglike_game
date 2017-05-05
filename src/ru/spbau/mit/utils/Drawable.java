package ru.spbau.mit.utils;

import com.googlecode.lanterna.TerminalPosition;
import ru.spbau.mit.core.GUI;

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
            GUI.addToTerminal(this);
        } catch (IOException e) {
            //TODO : WHERE AND HOW PROCESS THIS EXCEPTION
        }
    }

    public void redrawTo(TerminalPosition position) throws IOException {
        GUI.removeFromTerminal(this);
        this.current = position;
        GUI.addToTerminal(this);
    }

    public void clear() throws IOException {
        GUI.removeFromTerminal(this);
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
