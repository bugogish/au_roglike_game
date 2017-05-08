package ru.spbau.mit.GUI;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public final class TerminalGUI {
    private static final Logger logger = LogManager.getLogger(TerminalGUI.class);
    private static Terminal terminal = null;
    private static Screen screen = null;
    private static int maxX;
    private static int maxY;

    private TerminalGUI() {}

    public static void initialize() {
        if (terminal == null) {
            try {
                terminal = new DefaultTerminalFactory().createTerminal();
                terminal.setCursorVisible(false);
                screen = new TerminalScreen(terminal);
                screen.startScreen();
                maxX = screen.getTerminalSize().getRows();
                maxY = screen.getTerminalSize().getColumns();
            } catch (IOException e) {
                logger.fatal(e.getMessage());
                throw new RuntimeException();
            }
        }
    }

    public static int getMaxColumn() {
        return maxY;
    }

    public static int getMaxRow() {
        return maxX;
    }

    public static void addToTerminal(Drawable... items) {
        for (Drawable item : items) {
            screen.setCharacter(item.getCurrentPosition(),
                    new TextCharacter(item.getIcon(), TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        }

        try {
            screen.refresh();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static void removeFromTerminal(Drawable... items) {
        for (Drawable item : items) {
            screen.setCharacter(item.getCurrentPosition(),
                    new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        }

        try {
            screen.refresh();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    @Nullable
    public static KeyStroke readInput() {
        try {
            return screen.readInput();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    @NotNull
    public static MultiWindowTextGUI createNewScreen() {
        return new MultiWindowTextGUI(screen, TextColor.ANSI.BLACK);
    }

    public static void terminate() {
        try {
            terminal.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

