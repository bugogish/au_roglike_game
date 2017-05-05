package ru.spbau.mit.core;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.TextGUIGraphics;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.table.Table;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import ru.spbau.mit.utils.Drawable;

import java.io.IOException;

public class GUI {
    private static Terminal terminal = null;
    private static Screen screen = null;

    private GUI() {}

    public static void createTerminal() throws IOException {
        if (terminal == null) {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            screen.startScreen();
        }
    }

    public static int getMaxColumn() throws IOException {
        return terminal.getTerminalSize().getColumns();
    }

    public static int getMaxRow() throws IOException {
        return terminal.getTerminalSize().getRows();
    }

    public static void addToTerminal(Drawable... items) throws IOException {
        for (Drawable item : items) {
            terminal.setCursorPosition(item.getCurrentPosition());
            terminal.putCharacter(item.getIcon());
        }
        terminal.flush();
    }

    public static void removeFromTerminal(Drawable... items) throws IOException {
        for (Drawable item : items) {
            terminal.setCursorPosition(item.getCurrentPosition());
            terminal.putCharacter(' ');
        }
        terminal.flush();
    }

    public static KeyStroke readInput() throws IOException {
        return terminal.readInput();
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

