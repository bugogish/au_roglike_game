package ru.spbau.mit.GUI;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

/**
 * Class for GUI operations on terminal
 */

public final class TerminalGUI implements GUI {
    private static final Logger logger = LogManager.getLogger(TerminalGUI.class);
    private static Terminal terminal = null;
    private static Screen screen = null;
    private static int maxX;
    private static int maxY;

    public TerminalGUI() {
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

    @Override
    public int getMaxColumn() {
        return maxY;
    }

    @Override
    public int getMaxRow() {
        return maxX;
    }

    /**
     * Shows new non-interactive informational Screen
     * @param title - title of the new Screen
     * @param text - text that will be shown on new Screen
     */
    @Override
    public void showInfoScreen(String title, String text) {
        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);
        MessageDialog.showMessageDialog(textGUI, title, text);
        screen.clear();
    }

    @Override
    public void showOnScreen(Drawable item) {
        screen.setCharacter(item.getCurrentPosition(),
                new TextCharacter(item.getIcon(), TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        try {
            screen.refresh();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Shows drawable objects on terminal
     */
    @Override
    public void showOnScreen(Iterable<? extends Drawable> items) {
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

    @Override
    public void removeFromScreen(Drawable item) {
            screen.setCharacter(item.getCurrentPosition(),
                    new TextCharacter(' ', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

        try {
            screen.refresh();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * removes specified objects from terminal
     */
    @Override
    public void removeFromScreen(Iterable<? extends Drawable> items) {
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

    /**
     * @return key pressed by user
     */
    @Override
    @Nullable
    public KeyStroke readInput() {
        try {
            return screen.readInput();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Opens new interactive Screen
     */
    @NotNull
    public MultiWindowTextGUI openNewScreen() {
        return new MultiWindowTextGUI(screen, TextColor.ANSI.BLACK);
    }

    /**
     * terminates GUI
     */
    public void terminate() {
        try {
            terminal.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}

