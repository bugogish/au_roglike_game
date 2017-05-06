package ru.spbau.mit.core.GUI;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class TerminalGUI {
    private static Terminal terminal = null;
    private static Screen screen = null;
    private static int maxX;
    private static int maxY;

    private TerminalGUI() {}

    public static void initialize() throws IOException {
        if (terminal == null) {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();
            maxX = screen.getTerminalSize().getRows();
            maxY = screen.getTerminalSize().getColumns();
        }
    }

    public static int getMaxColumn() {
        return maxY;
    }

    public static int getMaxRow() {
        return maxX;
    }

    public static void addToTerminal(Drawable... items) throws IOException {
        for (Drawable item : items) {
            screen.setCharacter(item.getCurrentPosition(),
                    new TextCharacter(item.getIcon(), TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        }
        screen.refresh();
    }

    public static void removeFromTerminal(Drawable... items) throws IOException {
        for (Drawable item : items) {
            screen.setCharacter(item.getCurrentPosition(),
                    new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        }
        screen.refresh();
    }

    public static KeyStroke readInput() throws IOException {
        return screen.readInput();
    }

    public static TextGraphics createNewScreen() throws IOException {
        Screen screen = new TerminalScreen(terminal);
        screen.startScreen();
        return screen.newTextGraphics();
    }

    public static void closeNewScreen() throws IOException {
        screen.startScreen();
    }
}

