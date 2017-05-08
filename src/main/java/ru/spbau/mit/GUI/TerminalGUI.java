package ru.spbau.mit.GUI;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public final class TerminalGUI {
    private static Terminal terminal = null;
    private static Screen screen = null;
    private static int maxX;
    private static int maxY;

    private TerminalGUI() {}

    public static void initialize() throws IOException {
        if (terminal == null) {
            terminal = new DefaultTerminalFactory().createTerminal();
            terminal.setCursorVisible(false);
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

    @NotNull
    public static MultiWindowTextGUI createNewScreen() {
        return new MultiWindowTextGUI(screen, TextColor.ANSI.BLACK);
    }

    public static void terminate() throws IOException {
        terminal.close();
    }
}

